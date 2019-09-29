import { Component, OnInit, Inject } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { AppToastsService } from "src/app/services/toast.service";
import { AxiosInstance } from "axios";
import { FileUpload } from "primeng/fileupload";

@Component({
  selector: "app-destinatarios",
  templateUrl: "./destinatarios.component.html",
  styleUrls: ["./destinatarios.component.css"]
})
export class DestinatariosComponent implements OnInit {
  private routeName: string = "destinatarios";

  public cols: Array<any> = [
    { field: "name", header: "Nome" },
    { field: "email", header: "Email" },
    { field: "phone", header: "Telefone" },
    { field: "cpf", header: "CPF" }
  ];
  public rows: Array<any>;

  public hiddenUploadFile: boolean = false;
  public hiddenFormCadastrar: boolean = true;
  public hiddenTableEditar: boolean = true;
  public hiddenFormEditar: boolean = true;
  public hiddenTableExcluir: boolean = true;

  public cadastrarForm = new FormGroup({
    name: new FormControl(
      { value: "", disabled: false },
      Validators.compose([Validators.required])
    ),
    email: new FormControl(
      { value: "", disabled: false },
      Validators.compose([Validators.required, Validators.email])
    ),
    address: new FormControl(
      { value: "", disabled: false },
      Validators.compose([Validators.required])
    ),
    cpf: new FormControl(
      { value: "", disabled: false },
      Validators.compose([Validators.required])
    ),
    phone: new FormControl(
      { value: "", disabled: false },
      Validators.compose([Validators.required])
    )
  });
  public editarForm = new FormGroup({
    name: new FormControl(
      { value: "", disabled: false },
      Validators.compose([Validators.required])
    ),
    email: new FormControl(
      { value: "", disabled: false },
      Validators.compose([Validators.required, Validators.email])
    ),
    address: new FormControl(
      { value: "", disabled: false },
      Validators.compose([Validators.required])
    ),
    cpf: new FormControl(
      { value: "", disabled: false },
      Validators.compose([Validators.required])
    ),
    phone: new FormControl(
      { value: "", disabled: false },
      Validators.compose([Validators.required])
    )
  });

  public cloneRow: any = {
    id: "",
    name: "",
    email: "",
    cpf: "",
    phone: "",
    address: ""
  };

  constructor(
    private toast: AppToastsService,
    @Inject("Api") private api: AxiosInstance
  ) {
    this.setRows();
  }

  ngOnInit() {}

  async setRows() {
    const { data } = await this.api.get(this.routeName);
    this.rows = data;
  }
  handleUploadFile() {
    this.hiddenUploadFile = false;
    this.hiddenFormCadastrar = true;
    this.hiddenTableEditar = true;
    this.hiddenFormEditar = true;
    this.hiddenTableExcluir = true;
  }

  async handleSubmitUploadFile(fileUpload: FileUpload) {
    const [file] = fileUpload.files;
    const sendData = new FormData();
    sendData.append("file", file);
    try {
      const { data: rows } = await this.api.post(
        `${this.routeName}/upload`,
        sendData,
        {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        }
      );
      this.rows.push(...rows);
      fileUpload.clear();
      this.toast.addToast("success", "Sucesso", "Arquivo enviado com sucesso!");
      this.handleEditar();
    } catch (error) {
      this.toast.addToast(
        "error",
        "Atenção",
        "Aconteceu alguma problema ao subir o arquivo!"
      );
      console.error(error);
    }
  }

  handleCadastrar() {
    this.hiddenUploadFile = true;
    this.hiddenFormCadastrar = false;
    this.hiddenTableEditar = true;
    this.hiddenFormEditar = true;
    this.hiddenTableExcluir = true;
  }

  formattedCpf(cpf: string) {
    return cpf.replace(/^(\d{3})(\d{3})(\d{3})(\d{2})$/g, "$1.$2.$3-$4");
  }

  formattedPhone(phone: string) {
    return phone.replace(/^(\d{2})(\d{5})(\d{4})$/g, "($1)$2-$3");
  }

  async handleSubmitCadastrar() {
    if (!this.cadastrarForm.valid) {
      this.toast.addToast(
        "warn",
        "Atenção",
        "Preencha o formulário corretamente."
      );
      return;
    }
    const data = this.cadastrarForm.value;
    data.cpf = this.formattedCpf(data.cpf);
    data.phone = this.formattedPhone(data.phone);
    try {
      const { data: row } = await this.api.post(this.routeName, data);
      this.rows.push(row);
      this.toast.addToast(
        "success",
        "Sucesso",
        "Destinatario cadastrado com sucesso!"
      );
      this.cadastrarForm.reset();
    } catch (error) {
      if (error.response.status === 400)
        this.toast.addToast(
          "warn",
          "Atenção",
          "Já existe um destinatario com esse email."
        );
      else
        this.toast.addToast(
          "error",
          "Error",
          "Aconteceu algum erro, favor relatar."
        );
      console.error(error);
    }
  }

  handleEditar() {
    this.hiddenUploadFile = true;
    this.hiddenFormCadastrar = true;
    this.hiddenTableEditar = false;
    this.hiddenFormEditar = true;
    this.hiddenTableExcluir = true;
  }

  handleEditarTable(row) {
    this.cloneRow = Object.assign(row);
    this.hiddenTableEditar = true;
    this.hiddenFormEditar = false;
  }

  handleFormEditarCancelar() {
    this.hiddenTableEditar = false;
    this.hiddenFormEditar = true;
  }

  async handleSubmitEditar() {
    if (!this.editarForm.valid) {
      this.toast.addToast(
        "warn",
        "Atenção",
        "Preencha o formulário corretamente."
      );
      return;
    }
    const data = this.editarForm.value;
    data.cpf = this.formattedCpf(data.cpf);
    data.phone = this.formattedPhone(data.phone);
    data.id = this.cloneRow.id;
    try {
      const { data: rowUpdate } = await this.api.put(this.routeName, data);
      this.rows = this.rows.map(row => {
        if (row.id === rowUpdate.id) return rowUpdate;
        return row;
      });
      this.toast.addToast(
        "success",
        "Sucesso",
        "Destinatario editado com sucesso!"
      );
      this.editarForm.reset();
      this.handleEditar();
    } catch (error) {
      if (error.response.status === 400)
        this.toast.addToast(
          "warn",
          "Atenção",
          "Já existe um destinatario com esse email."
        );
      else
        this.toast.addToast(
          "error",
          "Error",
          "Aconteceu algum erro, favor relatar."
        );
      console.error(error);
    }
  }

  handleExcluir() {
    this.hiddenUploadFile = true;
    this.hiddenFormCadastrar = true;
    this.hiddenTableEditar = true;
    this.hiddenFormEditar = true;
    this.hiddenTableExcluir = false;
  }

  handleExcluirTable(row) {
    this.cloneRow = Object.assign(row);
  }

  async handleSubmitExcluir() {
    try {
      await this.api.delete(`${this.routeName}/${this.cloneRow.id}`);
      this.rows = this.rows.filter(row => row.id !== this.cloneRow.id);
      this.toast.addToast(
        "success",
        "Sucesso",
        "Remetente deletado com sucesso"
      );
    } catch (error) {
      if (error.response.status === 400)
        this.toast.addToast("warn", "Atenção", "Remetente não existe!");
      else
        this.toast.addToast(
          "error",
          "Error",
          "Aconteceu algum erro, favor relatar."
        );
      console.error(error);
    }
  }
}
