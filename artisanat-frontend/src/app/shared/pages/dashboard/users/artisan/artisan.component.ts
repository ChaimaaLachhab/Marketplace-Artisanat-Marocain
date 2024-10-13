import {Component, OnInit, ViewChild} from '@angular/core';
import {Table, TableModule} from "primeng/table";
import {Artisan} from "../../../../../core/models/artisan.model";
import {FormsModule} from "@angular/forms";
import {ConfirmationService, MessageService} from "primeng/api";
import {Router} from "@angular/router";
import {Specialty} from "../../../../../core/enums/specialty.enum";
import {VerificationStatus} from "../../../../../core/enums/verification-status.enum";
import {ArtisanService} from "../../../../../core/services/artisan.service";
import {ArtisanResponseDto} from "../../../../../core/dtos/response/artisan-response-dto";
import {InputTextModule} from "primeng/inputtext";
import {NgIf} from "@angular/common";
import {DropdownModule} from "primeng/dropdown";
import {TagModule} from "primeng/tag";
import {ButtonDirective} from "primeng/button";
import {Ripple} from "primeng/ripple";
import {ToastModule} from "primeng/toast";
import {UserService} from "../../../../../core/services/user.service";

@Component({
  selector: 'app-artisan',
  templateUrl: './artisan.component.html',
  standalone: true,
  imports: [
    TableModule,
    InputTextModule,
    FormsModule,
    NgIf,
    DropdownModule,
    TagModule,
    ButtonDirective,
    Ripple,
    ToastModule
  ],
  providers: [MessageService, ConfirmationService],
  styleUrls: ['./artisan.component.css']
})
export class ArtisanComponent implements OnInit {
  @ViewChild('dt') dt!: Table;
  artisans: ArtisanResponseDto[] = [];
  verifications = Object.values(VerificationStatus);
  clonedProduct: { [s: string]: ArtisanResponseDto } = {};

  constructor(
    private artisanService: ArtisanService,
    private userService: UserService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  onNumber(): number {
    return this.artisans.length;
  }

  loadProducts(): void {
    this.artisanService.getAllArtisans().subscribe({
      next: (artisan) => {
        this.artisans = artisan;
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Artisans loaded successfully' });
      },
      error: () => this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Failed to load artisans' })
    });
  }

  onCellEdit(artisan: any) {
    const updatedArtisan = {
      id: artisan.id,
      verificationStatus: artisan.verificationStatus
    };

    if (updatedArtisan.id && updatedArtisan.verificationStatus) {
      this.artisanService.verifyArtisan(updatedArtisan.id, updatedArtisan.verificationStatus).subscribe({
        next: () => {
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Artisan updated successfully' });
        },
        error: () => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Failed to update artisan' });
        }
      });
    } else {
      console.error('Invalid artisan data', updatedArtisan);
    }
  }


  getVerificationStatus(status: VerificationStatus): 'success' | 'warning' | 'danger' | undefined {
    switch (status) {
      case VerificationStatus.APPROVED:
        return 'success';
      case VerificationStatus.PENDING:
        return 'warning';
      case VerificationStatus.REJECTED:
        return 'danger';
      default:
        return undefined;
    }
  }

  getSpecialty(status: Specialty): 'success' | 'secondary' | 'info' | 'warning' | 'danger' | undefined {
    switch (status) {
      case Specialty.CARPET_MAKING:
        return 'success';
      case Specialty.EMBROIDERY:
        return 'secondary';
      case Specialty.GLASSBLOWING:
        return 'warning';
      case Specialty.FURNITURE_MAKING:
        return 'info';
      case Specialty.LEATHERWORK:
        return 'danger';
      case Specialty.STONEMASONRY:
        return 'success';
      case Specialty.JEWELRY_MAKING:
        return 'secondary';
      case Specialty.MARQUETRY:
        return 'warning';
      case Specialty.METALWORK:
        return 'info';
      case Specialty.WOOD_CARVING:
        return 'danger';
      default:
        return undefined;
    }
  }

  confirmDelete(artisan: Artisan): void {
    this.confirmationService.confirm({
      message: `Are you sure you want to delete the Artisan ${artisan.fullName}?`,
      accept: () => {
        this.userService.deleteUser(artisan.id).subscribe({
          next: () => {
            this.loadProducts();
            this.showSuccess('Artisan deleted successfully');
          },
          error: () => this.showError('Failed to delete Artisan')
        });
      }
    });
  }

  private showSuccess(message: string): void {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: message });
  }

  private showError(message: string): void {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: message });
  }
}
