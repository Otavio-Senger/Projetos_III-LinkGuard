import { useEffect, useState } from "react";

export default function DeviceList() {
  const [devices, setDevices] = useState([]);

  async function loadDevices() {
    try {
      const res = await fetch("http://localhost:8080/devices");
      const data = await res.json();
      setDevices(data);
    } catch (err) {
      console.log("Erro ao carregar dispositivos");
    }
  }

  useEffect(() => {
    loadDevices();
  }, []);

  async function runTest(id) {
    try {
      const res = await fetch(`http://localhost:8080/api/test/device/${id}`, {
        method: "POST"
      });

      if (!res.ok) {
        alert("Erro ao testar dispositivo");
        return;
      }

      alert("Teste executado!");
    } catch (e) {
      alert("Erro ao comunicar com o servidor");
    }
  }

  return (
    <div>
      <h3>Dispositivos Cadastrados</h3>

      {devices.map(dev => (
        <div className="box" key={dev.id}>
          <strong>{dev.name}</strong> <br />
          IP: {dev.ip_adress} <br />
          Tipo: {dev.type} <br />

          <button onClick={() => runTest(dev.id)}>
            Testar Conectividade
          </button>
        </div>
      ))}
    </div>
  );
}
