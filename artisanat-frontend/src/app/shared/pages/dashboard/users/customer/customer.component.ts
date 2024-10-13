import {Component, OnInit, ViewChild} from '@angular/core';
import {ButtonDirective} from "primeng/button";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {InputTextModule} from "primeng/inputtext";
import {ConfirmationService, MessageService, PrimeTemplate} from "primeng/api";
import {Table, TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";
import {UserService} from "../../../../../core/services/user.service";
import {CustomerResponseDto} from "../../../../../core/dtos/response/customer-response-dto";
import {CustomerService} from "../../../../../core/services/customer.service";
import {Ripple} from "primeng/ripple";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [
    ButtonDirective,
    ConfirmDialogModule,
    InputTextModule,
    PrimeTemplate,
    TableModule,
    ToastModule,
    Ripple,
    NgIf
  ],
  providers: [MessageService, ConfirmationService],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent implements OnInit{
  @ViewChild('dt') dt!: Table;
  customers: CustomerResponseDto[] = [];

  constructor(
    private customerService: CustomerService,
    private userService: UserService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
    this.loadCustomers();
  }

  onNumber(): number {
    return this.customers.length;
  }

  loadCustomers(): void {
    this.customerService.getAllCustomers().subscribe({
      next: (customers) => {
        this.customers = customers;
        console.log(customers)
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Customers loaded successfully' });
      },
      error: () => this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Failed to load customers' })
    });
  }

  confirmDelete(customer: CustomerResponseDto): void {
    this.confirmationService.confirm({
      message: `Are you sure you want to delete the customer ${customer.fullName}?`,
      accept: () => {
        this.userService.deleteUser(customer.id).subscribe({
          next: () => {
            this.loadCustomers();
            this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Customer deleted successfully' });
          },
          error: () => this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Failed to delete customer' })
        });
      }
    });
  }
}
