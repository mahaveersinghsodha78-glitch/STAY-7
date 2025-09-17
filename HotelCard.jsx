import React from 'react'

export default function HotelCard({ name, location, price }) {
  return (
    <div className="border rounded-lg p-4 shadow-md">
      <h2 className="text-lg font-bold">{name}</h2>
      <p>{location}</p>
      <p className="text-blue-600 font-semibold">${price} / night</p>
    </div>
  )
}