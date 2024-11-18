import React, { useState } from "react";
import BodegasListado from "./childrenComponents/BodegasListado";
import VinosListado from "./childrenComponents/VinosListado";
import bodegasServices from "../services/bodegas.services";
import Swal from "sweetalert2";

export default function Inicio() {
  const [action, setAction] = useState("C");
  const [bodegas, setBodegas] = useState([]);
  const [selectedBodegas, setSelectedBodegas] = useState([]);
  const [vinos, setVinos] = useState([]);

  const onBuscarBodega = async () => {
    console.log("Buscando bodegas...");
    try {
      const data = await bodegasServices.getBodegas();
      setBodegas(Array.isArray(data) ? data : []);
    } catch (error) {
      Swal.fire({
        title: error,
        icon: "error",
        confirmButtonText: "aceptar",
      });
    }
  };

  const onActualizarBodegas = async () => {
    console.log(selectedBodegas);
    if (selectedBodegas === undefined || selectedBodegas.length === 0) {
      alert("Por favor, seleccione una bodega.");
      return;
    }
    const data = await bodegasServices.postBodegas(selectedBodegas);
    setVinos(Array.isArray(data) ? data : []);
    setSelectedBodegas([]);
    setBodegas((prevBodegas) =>
      prevBodegas.filter((bodega) => !selectedBodegas.includes(bodega.id))
    );
  };

  const toggleBodegaSelection = (bodegaId) => {
    setSelectedBodegas((prevSelected) => {
      const newSelected = prevSelected.includes(bodegaId)
        ? prevSelected.filter((id) => id !== bodegaId)
        : [...prevSelected, bodegaId];
      return newSelected;
    });
  };

  return (
    <div className="container mt-3 ">
      <div className="row">
        {action === "C" && (
          <>
            <BodegasListado
              bodegas={bodegas}
              onBuscarBodega={onBuscarBodega}
              onToggleBodega={toggleBodegaSelection}
              onActualizarBodegas={onActualizarBodegas}
              selectedBodegas={selectedBodegas}
            />
            <VinosListado vinos={vinos} bodegas={bodegas} />
          </>
        )}
      </div>
    </div>
  );
}
