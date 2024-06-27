"use client";

import React, { useEffect, useState } from "react";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { useRouter } from 'next/navigation';

const SignIn = () => {
  const router = useRouter();
  const [userid, setUserid] = useState('');

  useEffect(() => {
    if (localStorage.getItem('userid')) {
      setUserid(localStorage.getItem('userid'));
      router.push('/account');
    }
  }, [userid]);

  const form = useForm({
    defaultValues: {
      username: "",
      password: ""
    },
  });

  function onSubmit(values) {
    fetch(`http://localhost:8080/api/v1/customers/login?username=${values.username}&password=${values.password}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
      })
    .then((res) => {
        return res.json(); 
    })
    .then((data) => {
        localStorage.setItem('userid', data.customerId);
        router.push(`/account`)
    });
  }
  return (
    <div className="flex justify-center items-center">
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8 w-1/2 mt-32">
          <FormField
            control={form.control}
            name="username"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Username</FormLabel>
                <FormControl>
                  <Input placeholder="Username" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="password"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Password</FormLabel>
                <FormControl>
                  <Input placeholder="Password" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <Button type="submit" className="w-full">Login</Button>
        </form>
      </Form>
    </div>
  );
};

export default SignIn;
