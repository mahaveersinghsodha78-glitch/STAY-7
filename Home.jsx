import React from 'react'
import HotelCard from '../components/HotelCard.jsx'

export default function Home() {
  return (
    <div className="p-6 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
      <HotelCard name="Luxury Stay" location="Jaipur" price={120} />
      <HotelCard name="Royal Palace" location="Jodhpur" price={150} />
      <HotelCard name="Desert View" location="Jaisalmer" price={90} />
    </div>
  )
}