import { Injectable } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable, BehaviorSubject, combineLatest } from 'rxjs';
import { map, startWith, tap, delay } from 'rxjs/operators';
import {GlobalLoadingService} from "src/app/shared/services/global-loading/global-loading.service";

@Injectable({
  providedIn: 'root'
})
export class GlobalSearchService {


  constructor(
    public globalLoading: GlobalLoadingService
  ) {
  }

  filterList<T>(source$: Observable<T[]>, searchControl: FormControl): Observable<T[]> {
    return combineLatest([
      source$,
      searchControl.valueChanges.pipe(startWith(''))
    ]).pipe(
      tap(() => this.globalLoading.show()),
      delay(50), // simulate processing delay
      map(([items, term]) => {
        if (!items || items.length === 0) {
          this.globalLoading.hide();
          return []; // 🔁 skip filtering, show nothing
        }

        const search = term.toLowerCase();
        const result = items.filter(item =>
          Object.values({
            ...item,
            createdAt: (item as any).createdAt
              ? new Date((item as any).createdAt).toLocaleString('de-DE')
              : ''
          }).some(val =>
            val?.toString().toLowerCase().includes(search)
          )
        );

        this.globalLoading.hide();
        return result;
      })
    );
  }

}
