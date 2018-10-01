import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Client } from './client';
import { CLIENTS } from './mock-clients';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

	private clientsUrl = '/api/clients';
	private risksUrl = '/api/clients/risks';

	getRisks(): Observable<String[]> {
		return this.http.get<String[]>(this.risksUrl)
			.pipe(
					tap(risks => this.log('fetched risks')),
					catchError(this.handleError('getRisks',[]))
				);
	}

	/** GET client by id. Will 404 if id not found */
	getClient(id: number): Observable<Client> {
	  	const url = `${this.clientsUrl}/${id}`;
	  	return this.http.get<Client>(url)
	  		.pipe(
	    		tap(id => this.log(`fetched client id=${id}`)),
	    		catchError(this.handleError<Client>(`getClient id=${id}`))
	  		);
	}

	getClients(): Observable<Client[]> {
		// this.messageService.add('ClientService: fetched clients');
		// return of(CLIENTS);
		return this.http.get<Client[]>(this.clientsUrl)
			.pipe(
					tap(clients => this.log('fetched clients')),
					catchError(this.handleError('getClients',[]))
				);
	}

	private handleError<T> (operation = 'operation', result?: T) {
	  return (error: any): Observable<T> => {
	 
	    console.error(error); 
	 
	    this.log(`${operation} failed: ${error.message}`);
	 
	    return of(result as T);
	  };
	}

	private log(message: string) {
	  	this.messageService.add(`ClientService: ${message}`);
	}

  constructor(private http: HttpClient,
  				private messageService: MessageService) { }
}
