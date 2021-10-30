import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CreateTransferComponent } from './components/create-transfer/create-transfer.component';
import { ListAccountsComponent } from './components/list-accounts/list-accounts.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateTransferComponent,
    ListAccountsComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
