import { Component, OnInit } from '@angular/core';
import { Client } from '../client';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  clients: Client[];

  selectedClient: Client;
  
  onSelect(client: Client): void {
  	this.selectedClient = client;
  }

  getClients(): void {
  	this.clientService.getClients()
  		.subscribe(clients => this.clients = clients);
  }

  constructor(private clientService : ClientService) { }

  ngOnInit() {
  	this.getClients();
  }

}
