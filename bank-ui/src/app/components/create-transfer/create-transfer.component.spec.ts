import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateTransferComponent} from './create-transfer.component';
import {FormsModule} from "@angular/forms";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";
import {By} from '@angular/platform-browser';
import {TransferService} from "../../service/transfer.service";
import {AccountService} from "../../service/account.service";
import {empty, of} from "rxjs";
//import { click, expectText, setFieldValue } from '../../spec-helpers/element.spec-helper';

import {
  expectedTransferRequest,
  transferaccs 
} from './mock.data';


describe('CreateTransferComponent', () => {
  let component: CreateTransferComponent;
  let fixture: ComponentFixture<CreateTransferComponent>;
  let serviceMock;
  
 

  beforeEach(async(() => {
    serviceMock = {findAll: jest.fn(),create: jest.fn()};
    serviceMock.findAll.mockReturnValueOnce(of(transferaccs));
    serviceMock.create.mockReturnValueOnce(of(111));
    serviceMock.open;
    TestBed.configureTestingModule({
      declarations: [CreateTransferComponent],
      imports: [FormsModule, MatSnackBarModule],
      providers :[
        {
          provide: TransferService,
          useValue: serviceMock
        },
        {
          provide: AccountService,
          useValue: serviceMock
        },
        {
          provide: MatSnackBar ,
          useValue: serviceMock
        }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTransferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(serviceMock.findAll).toHaveBeenCalledTimes(1);

    let selectSource: HTMLSelectElement = fixture.debugElement.query(By.css(".source-account-select")).nativeElement;
    expect(selectSource.options.length).toBe(2);
    let selectTarget: HTMLSelectElement = fixture.debugElement.query(By.css(".custom-select")).nativeElement;
    expect(selectTarget.options.length).toBe(2);
    expect(fixture.debugElement.query(By.css("h4")).nativeElement.textContent).toContain('Transfer');
    expect(component).toBeTruthy();    
  });


  
  it('should call service', () => {
    let selectSource: HTMLSelectElement = fixture.debugElement.query(By.css(".source-account-select")).nativeElement;
    selectSource.value = selectSource.options[0].value;
    selectSource.dispatchEvent(new Event('change'));

    let selectTarget: HTMLSelectElement = fixture.debugElement.query(By.css(".custom-select")).nativeElement;
    selectTarget.value = selectTarget.options[1].value;
    selectTarget.dispatchEvent(new Event('change'));

    let amountInput: HTMLInputElement = fixture.debugElement.query(By.css(".form-control")).nativeElement;

    amountInput.value='20';
    amountInput.dispatchEvent(new Event('input'));


    let button = fixture.debugElement.nativeElement.querySelector('button');
    button.click();

    fixture.detectChanges();
    
    fixture.whenStable().then(() => {
      let selectedS = selectSource.options[selectSource.selectedIndex].value;
      let selectedT = selectTarget.options[selectTarget.selectedIndex].value;
      expect(selectedS).toBe('A100');
      expect(selectedT).toBe('A200');
      expect(serviceMock.open).toHaveBeenCalledWith(expectedTransferRequest);
    });

  });
  
  
});
