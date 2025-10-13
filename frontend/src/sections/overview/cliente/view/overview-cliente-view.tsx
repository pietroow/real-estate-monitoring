import type { IClienteItem } from "src/types/cliente";

import { useState } from "react";

import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";

import { _clienteList } from "src/_mock/_cliente";
import { DashboardContent } from "src/layouts/dashboard";

import { Iconify } from "src/components/iconify";

import { ClienteListView } from "../cliente-list-view";
import { ClienteCreateModal } from "../cliente-create-modal";

export function OverviewClienteView() {
    const [clientes, setClientes] = useState(_clienteList);
    const [openModal, setOpenModal] = useState(false);
    const [editingCliente, setEditingCliente] = useState<IClienteItem | null>(null);

    const handleSaveCliente = (clienteData: Partial<IClienteItem>) => {
        if (editingCliente) {
            const clienteAtualizado = {
                ...editingCliente,
                ...clienteData,
            };
            setClientes(clientes.map(cliente =>
                cliente.id === editingCliente.id ? clienteAtualizado : cliente
            ));
        } else {
            const clienteCompleto: IClienteItem = {
                id: Date.now().toString(),
                createdAt: new Date(),
                ...clienteData,
            } as IClienteItem;
            setClientes([clienteCompleto, ...clientes]);
        }
        setOpenModal(false);
        setEditingCliente(null);
    };

    const handleDeleteCliente = (id: string) => {
        setClientes(clientes.filter(cliente => cliente.id !== id));
    };

    const handleEditCliente = (cliente: IClienteItem) => {
        setEditingCliente(cliente);
        setOpenModal(true);
    };

    const handleOpenCreateModal = () => {
        setEditingCliente(null);
        setOpenModal(true);
    };

    const handleCloseModal = () => {
        setOpenModal(false);
        setEditingCliente(null);
    };

    return (
        <DashboardContent>
            <Stack direction="row" alignItems="center" justifyContent="space-between" sx={{ mb: 3 }}>
                <Typography variant="h4" sx={{ color: 'text.primary' }}>
                    Clientes
                </Typography>
                <Button
                    variant="contained"
                    color="success"
                    startIcon={<Iconify icon="mingcute:add-line" />}
                    onClick={handleOpenCreateModal}
                    sx={{
                        bgcolor: 'success.main',
                        color: 'white',
                        textTransform: 'none',
                        fontWeight: 'medium',
                        px: 3,
                        '&:hover': {
                            bgcolor: 'success.dark',
                        }
                    }}
                >
                    Novo
                </Button>
            </Stack>

            <ClienteListView
                clientes={clientes}
                onEdit={handleEditCliente}
                onDelete={handleDeleteCliente}
            />

            <ClienteCreateModal
                open={openModal}
                onClose={handleCloseModal}
                onSave={handleSaveCliente}
                editingCliente={editingCliente}
            />
        </DashboardContent>
    );
}