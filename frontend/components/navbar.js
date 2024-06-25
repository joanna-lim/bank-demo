import React from "react";
import Image from 'next/image';
import Link from 'next/link'

const Navbar = () => {
  return (
    <div class="bg-gray-100 font-sans w-full m-0">
      <div class="bg-white shadow">
        <div class="mx-auto px-4">
          <div class="flex items-center justify-between py-4">
            <Link href="/account">
            <Image
              src="/citi_logo.png"
              width={70}
              height={70}
              alt="Citi Logo"
            />
            </Link>

            <div class="flex items-center">
              <Link
                href="/account"
                class="text-gray-800 text-sm font-semibold hover:text-purple-600 mr-6"
              >
                My Account
              </Link>
              <Link
                href="/transactions"
                class="text-gray-800 text-sm font-semibold hover:text-purple-600 mr-6"
              >
                Transactions
              </Link>
              <a
                href="/cards"
                class="text-gray-800 text-sm font-semibold hover:text-purple-600 mr-6"
              >
                Manage Cards
              </a>
              <a
                href="/transfer"
                class="text-gray-800 text-sm font-semibold hover:text-purple-600"
              >
                Transfer Money
              </a>
            </div>

            <div class="flex items-center">
              <a
                href="#"
                class="text-gray-800 text-sm font-semibold hover:text-purple-600 mr-6"
              >
                Sign in
              </a>
              <a
                href="#"
                class="text-gray-800 text-sm font-semibold border px-4 py-2 rounded-lg hover:text-purple-600 hover:border-purple-600"
              >
                Sign up
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Navbar;
