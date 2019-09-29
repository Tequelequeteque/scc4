import { Injectable } from "@angular/core";
import { MessageService } from "primeng/components/common/messageservice";

@Injectable({
  providedIn: "root"
})
export class AppToastsService {
  constructor(private messageService: MessageService) {}

  addToast(type, title, msg) {
    this.messageService.add({ severity: type, summary: title, detail: msg });
  }
  clear() {
    this.messageService.clear();
  }
}
