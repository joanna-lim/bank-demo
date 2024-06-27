"use client";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Overview } from "@/components/Overview";
import { useEffect, useState } from "react";

const Account = () => {
  const [accounts, setAccounts] = useState([]);
  const [isLoading, setLoading] = useState(true);

  useEffect(() => {
    const getAccounts = () => {
      fetch("http://localhost:8080/api/v1/bankaccounts/customer/1")
        .then((res) => res.json())
        .then((data) => {
          setAccounts(data);
          setLoading(false);
        });
    };
    getAccounts();
  }, []);

  return (
    <>
      <div className="flex-col flex justify-content-center items-center ">
        <div className="flex-1 w-full justify-content-center items-center space-y-4 p-8 pt-6">
          <div className="flex items-center justify-between space-y-2">
            <h2 className="text-3xl font-bold tracking-tight">Overview</h2>
            <div className="flex items-center space-x-2">
              <Button>Download statement</Button>
            </div>
          </div>

          <div className="space-y-4 ">
            <div className="grid gap-4 grid-cols-2">
              {accounts.map((account) => (
                <Card key={account.bankAccNum}>
                  <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
                    <CardTitle className="text-sm font-medium">
                      Account Number:{" "}
                      <span className="text-strong">{account.bankAccNum}</span>
                    </CardTitle>
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth="2"
                      className="h-4 w-4 text-muted-foreground"
                    >
                      <path d="M12 2v20M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6" />
                    </svg>
                  </CardHeader>
                  <CardContent>
                    <div className="text-2xl font-bold">${account.balance}</div>
                    <p className="text-xs text-muted-foreground">
                      +20.1% from last month
                    </p>
                  </CardContent>
                </Card>
              ))}
            </div>
            <div>
              <Card>
                <CardHeader>
                  <CardTitle>Spendings</CardTitle>
                </CardHeader>
                <CardContent className="pl-2">
                  <Overview />
                </CardContent>
              </Card>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Account;
