import {Component} from '@angular/core';
import {DataProviderService} from './data-provider.service';
import {Coins, Countries, ServiceInstanceResponse, Users} from './model';
import {flatMap, mergeMap, switchMap} from 'rxjs/operators';
import {forkJoin, interval, of, Subscription} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  accessToken: string;

  serviceInstances: ServiceInstanceResponse;
  subs: Subscription = new Subscription();


  constructor(
    private dataProvider: DataProviderService,
  ) {
    const username = 'admin';
    const password = 'admin';

    dataProvider.requestToken(
      'username=' + username + '&password=' + password + '&grant_type=password',
    ).subscribe(res => {
      this.accessToken = res.access_token;
      localStorage.setItem('accessToken', this.accessToken);
    });


    this.subs.add(
      interval(5000).pipe(
        switchMap(() => dataProvider.request<ServiceInstanceResponse>('/dashboard', 'GET'))
      ).subscribe(res => this.serviceInstances = res)
    );
  }


  start() {

    interval(3000).pipe(
      switchMap(() => this.dataProvider.request<Users>('/v1/users/nearby?radius=50', 'GET')),
      flatMap(({users}) => users),
      mergeMap(user => forkJoin({
        user: of(user),
        countries: this.dataProvider.request<Countries>('/v1/countries/' + user.username, 'GET')
          .pipe(flatMap(({countries}) => countries))
      })),
      mergeMap(({user, countries}) => this.dataProvider.request<Coins>(`/v1/coins?country=${countries}&username=${user.username}`, 'GET'))
    ).subscribe((result) => {
      console.log(result)
    });

  }

  stop () {
    this.subs.unsubscribe();
  }

}
