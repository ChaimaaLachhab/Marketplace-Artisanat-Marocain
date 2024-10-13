import { Component, OnInit, ViewChild } from '@angular/core';
import { ButtonDirective } from "primeng/button";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { DropdownModule } from "primeng/dropdown";
import { InputTextModule } from "primeng/inputtext";
import { NgIf } from "@angular/common";
import { Ripple } from "primeng/ripple";
import { Table, TableModule } from "primeng/table";
import { TagModule } from "primeng/tag";
import { ToastModule } from "primeng/toast";
import { FormsModule } from "@angular/forms";
import {UserService} from "../../../../../core/services/user.service";
import {CustomerResponseDto} from "../../../../../core/dtos/response/customer-response-dto";
import {ConfirmationService, MessageService} from "primeng/api";
import {Router} from "@angular/router";

@Component({
  selector: 'app-overview-user',
  standalone: true,
  imports: [
    ButtonDirective,
    ConfirmDialogModule,
    DropdownModule,
    InputTextModule,
    NgIf,
    Ripple,
    TableModule,
    TagModule,
    ToastModule,
    FormsModule
  ],
  providers: [MessageService, ConfirmationService],
  templateUrl: './overview-user.component.html',
  styleUrl: './overview-user.component.css'
})
export class OverviewUserComponent implements OnInit{
  @ViewChild('dt') dt!: Table;
  users: any[] = [];

  constructor(
    private userService: UserService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  onNumber(): number {
    return this.users.length;
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe({
      next: (users) => {
        this.users = users;
        console.log(users)
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Users loaded successfully' });
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
            this.loadUsers();
            this.messageService.add({ severity: 'success', summary: 'Success', detail: 'User deleted successfully' });
          },
          error: () => this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Failed to delete customer' })
        });
      }
    });
  }
}
