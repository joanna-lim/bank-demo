import React from "react";
import Image from 'next/image';
import Link from 'next/link'

const Navbar = () => {
  return (
    <div className="bg-gray-100 font-sans w-full m-0">
      <div className="bg-white shadow">
        <div className="mx-auto px-4">
          <div className="flex items-center justify-between py-4">
            <Link href="/account">
            <Image
              src="/citi_logo.png"
              width={70}
              height={70}
              alt="Citi Logo"
            />
            </Link>

            <div className="flex items-center">
              <Link
                href="/account"
                className="text-gray-800 text-sm font-semibold hover:text-purple-600 mr-6"
              >
                My Account
              </Link>
              <Link
                href="/transactions"
                className="text-gray-800 text-sm font-semibold hover:text-purple-600 mr-6"
              >
                Transactions
              </Link>
              <Link
                href="/cards"
                className="text-gray-800 text-sm font-semibold hover:text-purple-600 mr-6"
              >
                Manage Cards
              </Link>
              <Link
                href="/transfer"
                className="text-gray-800 text-sm font-semibold hover:text-purple-600"
              >
                Transfer Money
              </Link>
            </div>

            <div className="flex items-center">
              <a
                href="#"
                className="text-gray-800 text-sm font-semibold hover:text-purple-600 mr-6"
              >
                Sign in
              </a>
              <a
                href="#"
                className="text-gray-800 text-sm font-semibold border px-4 py-2 rounded-lg hover:text-purple-600 hover:border-purple-600"
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
