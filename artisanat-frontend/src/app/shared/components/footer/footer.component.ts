import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    ReactiveFormsModule
  ],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {
  footerLinks = [
    { name: 'Shop', url: '/shop' },
    { name: 'Collections', url: '/collections' },
    { name: 'About', url: '/about' }
  ];

  footerLinks2 = [

    { name: 'Contact', url: '/contact' },
    { name: 'Privacy', url: '/privacy' },
    { name: 'Terms', url: '/terms' }
  ];

  subscribeToNewsletter(email: string) {
    console.log('Subscribing email:', email);
  }
}
