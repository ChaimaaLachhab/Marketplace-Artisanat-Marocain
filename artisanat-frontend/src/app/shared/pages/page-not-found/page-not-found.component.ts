import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {FooterComponent} from "../../components/footer/footer.component";
import {HeaderComponent} from "../../components/header/header.component";

@Component({
  selector: 'app-page-not-found',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    FooterComponent,
    HeaderComponent
  ],
  templateUrl: './page-not-found.component.html',
  styleUrl: './page-not-found.component.css'
})
export class PageNotFoundComponent {
  socialIcons = [
    { name: 'Facebook', ariaLabel: 'Visit our Facebook page' },
    { name: 'Twitter', ariaLabel: 'Follow us on Twitter' },
    { name: 'Instagram', ariaLabel: 'Check our Instagram profile' },
    { name: 'LinkedIn', ariaLabel: 'Connect with us on LinkedIn' },
    { name: 'YouTube', ariaLabel: 'Subscribe to our YouTube channel' }
  ];

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
