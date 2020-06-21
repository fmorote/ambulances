import {Injectable} from '@angular/core';

import {Observable, of} from 'rxjs';

import {Transfer} from './transfer';
import {MessageService} from './message.service';
import {HttpClient} from "@angular/common/http";
import {catchError, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class TransferService {

  private transfersUrl = 'transfer'

  constructor(
    private http: HttpClient,
    private messageService: MessageService) {
  }

  getTransfers(): Observable<Transfer[]> {
    return this.http.get<Transfer[]>(this.transfersUrl)
      .pipe(
        tap(_ => this.log('fetched transfers')),
        catchError(this.handleError<Transfer[]>('getTransfers', []))
      );
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`TransferService: ${message}`);
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
