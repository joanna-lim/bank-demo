"use client";

import React, { useEffect, useState } from "react";
import Image from 'next/image';
import Link from 'next/link';
import { Button } from "@/components/ui/button";
import { useRouter } from 'next/navigation';

const Navbar = () => {
  const router = useRouter();

  const [userid, setUserid] = useState('');

  const logout = () => {
    localStorage.removeItem('userid');
    setUserid('');
    router.push('/signin');
  }

  useEffect(() => {
    setUserid(localStorage.getItem('userid'));
  },[localStorage.getItem('userid')])

  return (
    <div className="bg-gray-100 font-sans w-full m-0">
      <div className="bg-white shadow">
        <div className="mx-auto px-4">
          <div className="flex items-center justify-between py-4">
            <Link href="/account">
            <Image
              src="/onecity.png"
              width={100}
              height={100}
              alt="onecity Logo"
            />
            {/* <p className="font-bold text-2xl text-sky-400">OneCity</p> */}
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
                className="text-gray-800 text-sm font-semibold hover:text-purple-600 mr-6"
              >
                Transfer Money
              </Link>
            </div>

            <div className="flex items-center">
              {
                userid ? (
                  <Button
                    variant="ghost"
                    onClick={logout}
                    className="text-gray-800 text-sm font-semibold hover:text-purple-600 mr-6"
                  >
                    Logout
                  </Button>
                ) : (
                  <Link
                    href="/signin"
                    className="text-gray-800 text-sm font-semibold hover:text-purple-600 mr-6"
                  >
                    Sign in
                  </Link>
                )
              }
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Navbar;
