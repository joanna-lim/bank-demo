"use client";
import React, { useState } from "react";
import ReactCardFlip from "react-card-flip";
import { Button } from "@/components/ui/button";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel";
import CardFront from "@/components/cardFront";
import CardBack from "@/components/cardBack";

const CardPage = () => {
  const [flipped, setFlipped] = useState(false);
  const [currentCard, setCurrentCard] = useState("1234 5678 9012 3456");
  const handleClick = (e) => {
    setFlipped(!flipped);
    setCurrentCard("1234 5678 9012 3456")
  };
  const name = "John Doe";
  const cardNumber = "1234 5678 9012 3456";

  return (
    <div className="flex flex-col items-center justify-center mt-10">
      <Carousel className="w-full max-w-[510px]">
        <CarouselContent>
          {/* {Array.from({ length: 5 }).map((_, index) => ( */}
          <CarouselItem>
            <div className="p-1">
              
                  <ReactCardFlip isFlipped={flipped} flipDirection="vertical">
                    <div onClick={handleClick}>
                      <CardFront cardNumber={cardNumber} name={name}  />
                    </div>
                    <div onClick={handleClick}>
                      <CardBack cvv="233" expiry="11/27"/>
                    </div>
                  </ReactCardFlip>
                
            </div>
          </CarouselItem>
          <CarouselItem>
            <div className="p-2">
              
                  <ReactCardFlip isFlipped={flipped} flipDirection="vertical">
                    <div>
                      
                    </div>

                    <div>
                      This is the back of the card.
                      <button onClick={handleClick}>Click to flip</button>
                    </div>
                  </ReactCardFlip>
                
            </div>
          </CarouselItem>
          
          {/* ))} */}
        </CarouselContent>
        <CarouselPrevious />
        <CarouselNext />
      </Carousel>
      <Button variant="destructive" size="lg" className="mt-6">Deactivate Card</Button>
    </div>
  );
};

export default CardPage;
