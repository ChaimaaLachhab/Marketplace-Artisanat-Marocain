import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {AuthenticationService} from "../../../../core/services/authentication.service";
import {DividerModule} from "primeng/divider";
import {MatIcon} from "@angular/material/icon";
import {CustomerService} from "../../../../core/services/customer.service";
import {CustomerResponseDto} from "../../../../core/dtos/response/customer-response-dto";

@Component({
  selector: 'app-user-info',
  standalone: true,
    imports: [
        RouterLink,
        DividerModule,
        MatIcon
    ],
  templateUrl: './user-info.component.html',
  styleUrl: './user-info.component.css'
})
export class UserInfoComponent implements OnInit{
  userDetails: CustomerResponseDto | undefined;

  constructor(private router: Router, private customerService: CustomerService) {}

  ngOnInit(): void {
    this.customerService.getCustomerById().subscribe(decodedToken => {
      this.userDetails = decodedToken;
    });
  }
}
