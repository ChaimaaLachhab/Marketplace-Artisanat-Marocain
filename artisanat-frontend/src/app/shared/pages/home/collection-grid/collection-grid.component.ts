import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgForOf} from "@angular/common";
import {Collection} from "../../../../core/enums/collection.enum";
import {MatIcon} from "@angular/material/icon";

interface CollectionItem {
  title: string;
  description: string;
  imageUrl: string;
  icon: string;
  type: Collection;
}

@Component({
  selector: 'app-collection-grid',
  standalone: true,
  imports: [
    RouterLink,
    NgForOf,
    MatIcon
  ],
  templateUrl: './collection-grid.component.html',
  styleUrl: './collection-grid.component.css'
})
export class CollectionGridComponent {
  collections: CollectionItem[] = [
    {
      title: 'Home Decor',
      icon: 'https://cdn.youcan.shop/stores/1cd922d79a912031a6426d10c804a137/others/EJeFtA23CuifcJ0LSNyjVDCEhs9P5eN1IRBe5NI3.png',
      description: 'Elegant home decor items that add a touch of luxury to any space.',
      imageUrl: 'https://cdn.builder.io/api/v1/image/assets/TEMP/71b49e8f9812d2b275423f111960672afdcc7d898b91cc79e092b1d3343fb499',
      type: Collection.HOME_DECOR
    },
    {
      title: 'Accessories',
      icon: 'https://cdn.youcan.shop/stores/1cd922d79a912031a6426d10c804a137/others/EJeFtA23CuifcJ0LSNyjVDCEhs9P5eN1IRBe5NI3.png',
      description: 'Stunning accessories that reflect your unique style and personality.',
      imageUrl: 'https://cdn.builder.io/api/v1/image/assets/TEMP/22936519758ef731c563dcdd5aa7f919c323190c9e6162f7b61e677dc0fd18e4',
      type: Collection.ACCESSORIES
    },
    {
      title: 'Clothing',
      icon: 'https://cdn.youcan.shop/stores/1cd922d79a912031a6426d10c804a137/others/EJeFtA23CuifcJ0LSNyjVDCEhs9P5eN1IRBe5NI3.png',
      description: 'Timeless keepsakes and collectibles to treasure for years to come.',
      imageUrl: 'https://cdn.builder.io/api/v1/image/assets/TEMP/a7287af960a48c3770907a53fed6a9ea68663202214cb13a4a173d9703ed906b',
      type: Collection.CLOTHING
    },
    {
      title: 'Kitchen Ware',
      icon: 'https://cdn.youcan.shop/stores/1cd922d79a912031a6426d10c804a137/others/EJeFtA23CuifcJ0LSNyjVDCEhs9P5eN1IRBe5NI3.png',
      description: 'Delightful jewelry pieces that add sparkle to any outfit.',
      imageUrl: 'https://cdn.builder.io/api/v1/image/assets/TEMP/1c393e405f448c25848f389ba9aff361d37baf22612c897149d6e720e611fb7b',
      type: Collection.KITCHENWARE
    },
    {
      title: 'Furniture',
      icon: 'https://cdn.youcan.shop/stores/1cd922d79a912031a6426d10c804a137/others/EJeFtA23CuifcJ0LSNyjVDCEhs9P5eN1IRBe5NI3.png',
      description: 'Classic furniture pieces that stand the test of time.',
      imageUrl: 'https://cdn.builder.io/api/v1/image/assets/TEMP/eeed1df646fad552f9d036d01224f759a757b718776e05c19aa814f197b038d6',
      type: Collection.FURNITURE
    },
    {
      title: 'Moroccan Artisan',
      icon: 'https://cdn.youcan.shop/stores/1cd922d79a912031a6426d10c804a137/others/EJeFtA23CuifcJ0LSNyjVDCEhs9P5eN1IRBe5NI3.png',
      description: 'Authentic handcrafted items showcasing the rich artisanal tradition of Morocco.',
      imageUrl: 'https://cdn.builder.io/api/v1/image/assets/TEMP/470b634f27fedfd3109672c312eafb7f1f78b67d5e36ca1189a4cfd3ac397335',
      type: Collection.ACCESSORIES
    }
  ];
}
