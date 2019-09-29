import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { RemetentesComponent } from "./remetentes.component";

describe("RemetentesComponent", () => {
  let component: RemetentesComponent;
  let fixture: ComponentFixture<RemetentesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RemetentesComponent]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemetentesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
