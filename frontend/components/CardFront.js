import React from "react";
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
  } from "@/components/ui/card";

const CardFront = ({ cardNumber, name}) => {

    return (
        <div>
        <Card className="w-[500px] bg-teal-100 border-2 border-black" >
            <CardHeader>
            <CardTitle className="flex justify-between text-3xl">
                <p>Citibank</p>
                <p>Global wallet</p>
            </CardTitle>
            </CardHeader>
            <CardContent className="my-14 text-xl tracking-widest">
            {cardNumber}
            </CardContent>
            <CardFooter className="flex justify-between">
            <p>{name}</p>
            <p>Debit</p>
            </CardFooter>
        </Card>
        </div>
    );
};

export default CardFront;
