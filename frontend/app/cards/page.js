"use client";
import React, { useState, useEffect } from "react";
import ReactCardFlip from "react-card-flip";
import { Button } from "@/components/ui/button";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel";
import CardFront from "@/components/CardFront";
import CardBack from "@/components/CardBack";
import { useRouter } from 'next/navigation'
import Link from "next/link";


const CardPage = () => {
  const [flipped, setFlipped] = useState(false);
  const handleClick = (e) => {
    setFlipped(!flipped);
  };
  const router = useRouter();


  const [cards, setCards] = useState([]);
  const [isLoading, setLoading] = useState(true);
  const [userid, setUserid] = useState('');
  const [name, setName] = useState('');

  useEffect(() => {
    setUserid(localStorage.getItem('userid'));
    setName(localStorage.getItem('name'));
    const getCards = () => {
      fetch(`http://localhost:8080/api/v1/cards/${localStorage.getItem('userid')}`, { cache: 'no-store' })
        .then((res) => res.json())
        .then((data) => {
          setCards(data);
          setLoading(false);
        });
    };
    if (localStorage.getItem('userid')) {
      getCards();
    }
  }, []);

  const deactivateCard = (cardId) => {
    console.log(cardId)
    fetch(`http://localhost:8080/api/v1/cards/block/${cardId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then((res) => {
      getCards();
      router.refresh();
      res.json(); 
    })
  }

  const activateCard = (cardId) => {
    console.log(cardId)
    fetch(`http://localhost:8080/api/v1/cards/activate/${cardId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then((res) => {
      getCards();
      router.refresh();
      res.json(); 
    })
  }

  const getCards = () => {
    fetch(`http://localhost:8080/api/v1/cards/${localStorage.getItem('userid')}`, { cache: 'no-store' })
      .then((res) => res.json())
      .then((data) => {
        setCards(data);
        setLoading(false);
      });
  };




  return (
    <>
    {userid ?
    (<div className="flex flex-col items-center justify-center mt-16">
      <Carousel className="w-full max-w-[510px]">
        <CarouselContent>
        {cards?.map((card) => (
          <CarouselItem className="flex flex-col" key={card.cardId}>
            <div className="p-1">
                  <ReactCardFlip isFlipped={flipped} flipDirection="vertical">
                    <div onClick={handleClick}>
                      <CardFront cardNumber={card.cardNumber.toString().replace(/\d{4}(?=.)/g, '$& ')} name={name}  />
                    </div>
                    <div onClick={handleClick}>
                      <CardBack cvv={card.cvv} expiry={card.expiry}/>
                    </div>
                  </ReactCardFlip>               
            </div>
            {card.active ?  
            <Button variant="destructive" size="lg" className="mt-6" onClick={()=>deactivateCard(card.cardId)}>Deactivate Card</Button>
            : <Button size="lg" className="mt-6" onClick={()=>activateCard(card.cardId)}>Activate Card</Button>}
          </CarouselItem>
      ))}
          
          {/* ))} */}
        </CarouselContent>
        <CarouselPrevious />
        <CarouselNext />
      </Carousel>
    </div>):
      (<div className="flex flex-col justify-center items-center text-center mt-52">
        <p className="text-4xl font-semibold"> Please sign in to view your account</p> 
        <Button asChild className="mx-2 w-2/5 text-2xl p-8 mt-6" size="lg">
          <Link href="/signin">Sign in</Link>
        </Button>
      </div>)
    
    }
    </>
  );
};

export default CardPage;
