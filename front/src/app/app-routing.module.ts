import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { SobreComponent } from "./pages/sobre/sobre.component";
import { RemetentesComponent } from "./pages/remetentes/remetentes.component";
import { DestinatariosComponent } from "./pages/destinatarios/destinatarios.component";
import { MensagensComponent } from "./pages/mensagens/mensagens.component";

const routes: Routes = [
  { path: "remetentes", component: RemetentesComponent },
  { path: "destinatarios", component: DestinatariosComponent },
  { path: "mensagens", component: MensagensComponent },
  { path: "sobre", component: SobreComponent },
  { path: "**", redirectTo: "sobre", pathMatch: "full" }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      routes
      //{ enableTracing: true }
    )
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
