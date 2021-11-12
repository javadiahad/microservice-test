import {Component, OnInit} from '@angular/core';
import {TransferRequest} from '../../models/transferRequest';
import {TransferService} from "../../service/transfer.service";
import {MatSnackBar} from "@angular/material";
import {AccountService} from "../../service/account.service";
import {Account} from "../../models/Account";

@Component({
  selector: 'app-create-transfer',
  templateUrl: './create-transfer.component.html',
  styleUrls: ['./create-transfer.component.css']
})
export class CreateTransferComponent implements OnInit {
  readonly transfer: TransferRequest;
  accounts: Account[] = [];
  private readonly snackbar: MatSnackBar;

  constructor(
    private readonly transferService: TransferService,
    private readonly accountService: AccountService,
    snackbar: MatSnackBar
  ) {
    this.snackbar = snackbar;
    this.transfer = new TransferRequest();
    this.transfer.accountFrom = "A100";
    this.transfer.accountTo = "A200";
    this.transfer.amount = 100;
    this._easyTransferAlertEnabled = this.initEasyTransferAlertEnabled();
  }

  private _easyTransferAlertEnabled: boolean;

  get easyTransferAlertEnabled(): boolean {
    return this._easyTransferAlertEnabled;
  }

  set easyTransferAlertEnabled(value: boolean) {
    this._easyTransferAlertEnabled = value;
    localStorage.setItem("easyTransferAlertEnabled", String(value));
  }

  private initEasyTransferAlertEnabled(): boolean {
    let item = localStorage.getItem("easyTransferAlertEnabled");
    return item == undefined || item == 'true';
  }

  ngOnInit() {
    this.initAccounts();
  }

  onSubmit(): void {
    this.transferService.create(this.transfer)
      .subscribe(res => {
        this.snackbar.open('Transfer completed.', null, {
          duration: 3000
        });
      }, err => {
        this.snackbar.open('Error during exchanging transfer', null, {
          duration: 3000
        });
      });
  }

  private initAccounts() {
    this.accountService.findAll()
      .subscribe(
        (acc: Account[]) => this.accounts = acc,
        err => console.error(err)
      );
  }

}
