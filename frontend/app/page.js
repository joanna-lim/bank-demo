import { Divide } from "lucide-react";
import Image from "next/image";
import { Button } from "@/components/ui/button";
import Link from "next/link";

export default function Home() {

  return (
    <div>
      <div className="flex justify-center items-center mt-24 ">
        <div className="text-center">
          <h1 className="text-5xl font-bold my-2">Welcome to</h1>
          <h1 className="text-5xl font-bold my-2">Singapore Team 1</h1>
          <h1 className="text-5xl font-bold my-2">Hackathon Project</h1>
        </div>
      </div>
      <div className="flex justify-center items-center mt-20">
        <Button asChild className="mx-2 w-2/5 text-2xl p-8" size="lg">
          <Link href="/signin">Sign in</Link>
        </Button>
      </div>
    </div>
  );
}
