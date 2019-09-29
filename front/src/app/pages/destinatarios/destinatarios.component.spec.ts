import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { DestinatariosComponent } from "./destinatarios.component";

describe("DestinatarioComponent", () => {
  let component: DestinatariosComponent;
  let fixture: ComponentFixture<DestinatariosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [DestinatariosComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DestinatariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
