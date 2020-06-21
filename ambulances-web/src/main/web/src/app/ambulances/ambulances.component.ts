import {Component, OnInit} from '@angular/core';
import {Transfer} from "../transfer";
import {TransferService} from "../transfer.service";
import {MessageService} from "../message.service";

@Component({
  selector: 'app-ambulances',
  templateUrl: './ambulances.component.html',
  styleUrls: ['./ambulances.component.css']
})
export class AmbulancesComponent implements OnInit {

  transfers: Transfer[];
  selectedTransfer: Transfer;

  constructor(private transferService: TransferService, private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.getTransfers();
  }

  onSelect(transfer: Transfer): void {
    this.selectedTransfer = transfer;
    this.messageService.add(`TransferService: Selected transfer id=${transfer.id}`);
  }

  getTransfers(): void {
    this.transferService.getTransfers()
      .subscribe(transfers => this.transfers = transfers);
  }
}
