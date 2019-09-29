import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { TableModule } from "primeng/table";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { InputTextareaModule } from "primeng/inputtextarea";
import { MessageService } from "primeng/components/common/messageservice";
import { ToastModule } from "primeng/toast";
import { DialogModule } from "primeng/dialog";
import { FileUploadModule } from "primeng/fileupload";
import { HttpClientModule } from "@angular/common/http";

import { NgxMaskModule, IConfig } from "ngx-mask";

import Api from "./services/api.service";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { RemetentesComponent } from "./pages/remetentes/remetentes.component";
import { NavBarComponent } from "./components/nav-bar/nav-bar.component";
import { SobreComponent } from "./pages/sobre/sobre.component";
import { AppToastsService } from "./services/toast.service";
import { DestinatariosComponent } from "./pages/destinatarios/destinatarios.component";
import { MensagensComponent } from "./pages/mensagens/mensagens.component";

export let options: Partial<IConfig> | (() => Partial<IConfig>);

@NgModule({
  declarations: [
    AppComponent,
    RemetentesComponent,
    NavBarComponent,
    SobreComponent,
    DestinatariosComponent,
    MensagensComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    TableModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    InputTextModule,
    InputTextareaModule,
    ToastModule,
    DialogModule,
    FileUploadModule,
    HttpClientModule,
    NgxMaskModule.forRoot(options)
  ],
  providers: [
    { provide: "Api", useValue: Api },
    MessageService,
    AppToastsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
