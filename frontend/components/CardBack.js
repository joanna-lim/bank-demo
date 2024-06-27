import React from "react";
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
  } from "@/components/ui/card";

const CardBack = ({ cvv, expiry }) => {

    return (
        <div>
        <Card className="w-[500px] bg-teal-100 border-2 border-black" >
            <CardHeader>
            <CardTitle className="flex justify-between text-2xl">
                <div className="bg-slate-800 w-full p-[26px]">
                </div>
            </CardTitle>
            </CardHeader>
            <CardContent className="flex my-12 text-xl tracking-widest justify-between font-bold" >
                <p>Exp: {expiry}</p>
                <p>CVV {cvv}</p>
            </CardContent>
            <CardFooter className="flex justify-between font-bold text-xl">
            <p>NETS</p>
            <p>Debit</p>
            </CardFooter>
        </Card>
        </div>
    );
};

export default CardBack;
