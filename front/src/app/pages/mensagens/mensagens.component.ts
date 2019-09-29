import { Component, OnInit, Inject } from "@angular/core";
import { AppToastsService } from "src/app/services/toast.service";
import { AxiosInstance } from "axios";

@Component({
  selector: "app-mensagens",
  templateUrl: "./mensagens.component.html",
  styleUrls: ["./mensagens.component.css"]
})
export class MensagensComponent implements OnInit {
  private routeRemetente: string = "remetentes";
  private routeDestinatario: string = "destinatarios";
  private routeMensagem: string = "mensagens";

  public cols: Array<any> = [
    { field: "name", header: "Nome" },
    { field: "email", header: "Email" },
    { field: "phone", header: "Telefone" },
    { field: "cpf", header: "CPF" }
  ];

  public hiddenEscolherRemetente: boolean = false;
  public hiddenEscolherDestinatarios: boolean = true;
  public hiddenInputMensagem: boolean = true;

  public remetentes: Array<any>;
  public remetenteSelecionado: any;
  public destinatarios: Array<any>;
  public destinatariosSelecionados: Array<any>;
  public mensagem: string = "";

  constructor(
    private toast: AppToastsService,
    @Inject("Api") private api: AxiosInstance
  ) {
    this.setRemetentes();
    this.setDestinatarios();
  }

  ngOnInit() {}

  async setRemetentes() {
    const { data } = await this.api.get(this.routeRemetente);
    this.remetentes = data;
  }

  async setDestinatarios() {
    const { data } = await this.api.get(this.routeDestinatario);
    this.destinatarios = data;
  }

  handleRemetente() {
    this.hiddenEscolherRemetente = false;
    this.hiddenEscolherDestinatarios = true;
    this.hiddenInputMensagem = true;
  }

  handleDestinatarios() {
    this.hiddenEscolherRemetente = true;
    this.hiddenEscolherDestinatarios = false;
    this.hiddenInputMensagem = true;
  }

  handleMensagem() {
    this.hiddenEscolherRemetente = true;
    this.hiddenEscolherDestinatarios = true;
    this.hiddenInputMensagem = false;
  }

  async handleSubmitMensagem() {
    if (this.check()) return;
    try {
      await this.api.put(
        `${this.routeRemetente}/${this.remetenteSelecionado.id}/${this.routeMensagem}`,
        {
          destinatarios: this.destinatariosSelecionados.map(d => d.id),
          mensagem: this.mensagem
        }
      );
      this.toast.addToast("success", "Sucesso", "Mensagem enviada com sucesso");
    } catch (error) {
      this.toast.addToast(
        "error",
        "Atenção",
        "Aconteceu alguma falha ao enviar a mensagem."
      );
      console.error(error);
    }
  }

  check(): boolean {
    let flag = false;
    if (!this.remetenteSelecionado) {
      this.toast.addToast("warn", "Atenção", "Escolha um Remetente!");
      flag = true;
    }
    if (
      !this.destinatariosSelecionados ||
      !this.destinatariosSelecionados.length
    ) {
      this.toast.addToast(
        "warn",
        "Atenção",
        "Escolha um ou mais Destinatários!"
      );
      flag = true;
    }
    return flag;
  }
}
