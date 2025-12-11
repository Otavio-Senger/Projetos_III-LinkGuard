import { useState } from "react";

export default function DeviceForm() {
  const [name, setName] = useState("");
  const [ip, setIp] = useState("");
  const [type, setType] = useState("pc");

  async function handleSubmit(e) {
    e.preventDefault();

    const body = {
      name,
      ip_adress: ip,
      type
    };

    try {
      const res = await fetch("http://localhost:8080/devices", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body)
      });

      if (!res.ok) {
        alert("Erro ao cadastrar dispositivo");
        return;
      }

      alert("Dispositivo cadastrado!");
      setName("");
      setIp("");
      setType("pc");

    } catch (e) {
      alert("Erro ao conectar com o servidor");
    }
  }

  return (
    <form onSubmit={handleSubmit}>
      <h3>Cadastrar Dispositivo</h3>

      <input
        placeholder="Nome"
        value={name}
        onChange={e => setName(e.target.value)}
      />

      <input
        placeholder="Endereço IP"
        value={ip}
        onChange={e => setIp(e.target.value)}
      />

      <select value={type} onChange={e => setType(e.target.value)}>
        <option value="pc">PC</option>
        <option value="impressora">Impressora</option>
        <option value="celular">Celular</option>
        <option value="roteador">Roteador</option>
        <option value="outros">Outros</option>
      </select>

      <button type="submit">Cadastrar</button>
    </form>
  );
}
