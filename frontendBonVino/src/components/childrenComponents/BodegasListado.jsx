import React from "react";
import Table from "react-bootstrap/Table";

export default function BodegasListado({
  bodegas,
  onBuscarBodega,
  onToggleBodega,
  selectedBodegas,
  onActualizarBodegas,
}) {
  if (!bodegas || bodegas.length === 0) {
    return (
      <div className="col-4" style={{ marginRight: 50 }}>
        <div className="header-container p-3 mb-2 bg-primary text-white rounded d-flex justify-content-between align-items-center">
          <h2 className="mb-0">Bodegas</h2>
          <button
            type="button"
            style={{ width: 150, background: "green", marginLeft: 50 }}
            className="btn btn-secondary"
            onClick={onBuscarBodega}
          >
            Buscar Bodegas
          </button>
        </div>
        <p>No hay bodegas cargadas aún</p>
      </div>
    );
  }

  const handleCheckboxChange = (event, bodegaId) => {
    onToggleBodega(event, bodegaId);
  };

  const isChecked = (id) => selectedBodegas.includes(id);

  const tbody = bodegas.map((bodega) => (
    <tr key={bodega.id}>
      <td>
        <input
          type="checkbox"
          checked={isChecked(bodega.id)}
          onChange={() => handleCheckboxChange(bodega.id)}
          style={{ width: 20, height: 20, cursor: "pointer" }}
          className="d-flex justify-content-center"
        />
      </td>
      <td>{bodega.nombre}</td>
    </tr>
  ));

  return (
    <div style={{ marginRight: 50 }} className="col-4">
      <div className="header-container p-3 mb-2 bg-primary text-white rounded d-flex justify-content-between align-items-center">
        <h2 className="mb-0">Bodegas</h2>
        <button
          type="button"
          style={{ width: 150, background: "green", marginLeft: 50 }}
          className="btn btn-secondary"
        >
          Buscar Bodega
        </button>
      </div>
      <table className="table">
        <thead>
          <tr>
            <th>Seleccionar</th>
            <th>Nombre Bodega</th>
          </tr>
        </thead>
        <tbody>{tbody}</tbody>
      </table>
      <button
        type="button"
        className="btn btn-primary"
        onClick={onActualizarBodegas}
      >
        Actualizar Bodegas
      </button>
    </div>
  );
}
