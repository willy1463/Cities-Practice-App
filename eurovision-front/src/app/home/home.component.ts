import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
  welcomeMessage = 'Welcome to Eurovision Services';
  redirectMessage = 'Go to Cities list!';

  constructor() { }

  ngOnInit(): void {
  }

}
