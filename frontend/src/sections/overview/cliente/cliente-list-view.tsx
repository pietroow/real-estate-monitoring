import type { TableHeadCellProps } from "src/components/table";
import type { IClienteItem, IClienteTableFilters } from "src/types/cliente";

import { useState } from "react";

import Card from "@mui/material/Card";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";

import { TableHeadCustom, TablePaginationCustom } from "src/components/table";

import { ClienteTableRow } from "./cliente-table-row";
import { ClienteTableToolbar } from "./cliente-table-toolbar";


const TABLE_HEAD: TableHeadCellProps[] = [
  { id: "nome", label: "Nome/Razão Social", width: 250 },
  { id: "contato", label: "Contato", width: 200 },
  { id: "telefone", label: "Telefone", width: 180 },
  { id: "email", label: "E-mail", width: 250 },
  { id: "fonte", label: "Fonte", width: 150 },
  { id: "actions", label: "Ações", width: 80, align: "center" },
];

type Props = {
    clientes: IClienteItem[];
    onEdit: (cliente: IClienteItem) => void;
    onDelete: (id: string) => void;
};

export function ClienteListView({ clientes, onEdit, onDelete }: Props) {
    const [filters, setFilters] = useState<IClienteTableFilters>({name:'', status:'all'});
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);

    const filteredClientes = clientes.filter((cliente)=>{
        if (filters.name) {
            if (!cliente.nome?.toLowerCase().includes(filters.name.toLowerCase())){
                return false;
            }
        }
        if(filters.status !== 'all'&& cliente.status !== filters.status){
            return false;
        }
        return true;
    });

    const paginatedClientes = filteredClientes.slice(
        page * rowsPerPage,
        page * rowsPerPage + rowsPerPage
    );

    return(
        <Card>
            <ClienteTableToolbar
                filters={filters}
                onFilters={(key: keyof IClienteTableFilters, value: string) => setFilters({ ...filters, [key]: value })}
            />
            <Table>
                <TableHeadCustom headCells={TABLE_HEAD}/>
                <TableBody>
                    {paginatedClientes.map((cliente)=>(
                        <ClienteTableRow
                            key={cliente.id}
                            row={cliente}
                            onDelete={onDelete}
                            onEdit={onEdit}
                        />
                    ))}
                </TableBody>
            </Table>
            <TablePaginationCustom
                count={filteredClientes.length}
                page={page}
                rowsPerPage={rowsPerPage}
                onPageChange={(event, newPage) => setPage(newPage)}
                onRowsPerPageChange={(event) => {
                    setRowsPerPage(parseInt(event.target.value, 10));
                    setPage(0);
                }}
            />
        </Card>
    );
}