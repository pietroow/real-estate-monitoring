import type { IFuncionarioItem, IFuncionarioTableFilters } from 'src/types/funcionario';

import { useState, useEffect, useCallback } from 'react';
import { useBoolean, useSetState } from 'minimal-shared/hooks';

import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import Table from '@mui/material/Table';
import Button from '@mui/material/Button';
import Skeleton from '@mui/material/Skeleton';
import TableRow from '@mui/material/TableRow';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import Typography from '@mui/material/Typography';
import TableContainer from '@mui/material/TableContainer';

import { paths } from 'src/routes/paths';

import { DashboardContent } from 'src/layouts/dashboard';
import { _funcionarioList } from 'src/_mock/_funcionarios';

import { toast } from 'src/components/snackbar';
import { Iconify } from 'src/components/iconify';
import { ConfirmDialog } from 'src/components/custom-dialog';
import { CustomBreadcrumbs } from 'src/components/custom-breadcrumbs';
import {
    useTable,
    rowInPage,
    getComparator,
    TablePaginationCustom,
} from 'src/components/table';

import { FuncionarioTableRow } from './funcionario-table-row';
import { FuncionarioTableToolbar } from './funcionario-table-toolbar';

type Props = {
    onNewFuncionario?: () => void;
    onEditFuncionario?: (funcionario: IFuncionarioItem) => void;
    onFuncionarioCreated?: (funcionario: IFuncionarioItem) => void;
};

export function FuncionarioListView({ onNewFuncionario, onEditFuncionario, onFuncionarioCreated }: Props) {
    const table = useTable();

    const confirmDialog = useBoolean();

    const [tableData, setTableData] = useState<IFuncionarioItem[]>(_funcionarioList);
    const [isLoading] = useState(false);

    useEffect(() => {
        const handleNewFuncionario = (event: any) => {
            console.log('Evento recebido na lista:', event.detail);
            if (event.detail) {
                setTableData(prevData => {
                    console.log('Adicionando funcionário à lista:', event.detail);
                    return [event.detail, ...prevData];
                });
            }
        };

        const handleUpdatedFuncionario = (event: any) => {
            console.log('Evento de atualização recebido na lista:', event.detail);
            if (event.detail) {
                setTableData(prevData => {
                    console.log('Atualizando funcionário na lista:', event.detail);
                    return prevData.map(funcionario =>
                        funcionario.id === event.detail.id ? event.detail : funcionario
                    );
                });
            }
        };

        window.addEventListener('funcionarioCreated', handleNewFuncionario);
        window.addEventListener('funcionarioUpdated', handleUpdatedFuncionario);

        return () => {
            window.removeEventListener('funcionarioCreated', handleNewFuncionario);
            window.removeEventListener('funcionarioUpdated', handleUpdatedFuncionario);
        };
    }, []);

    const filters = useSetState<IFuncionarioTableFilters>({
        name: '',
        status: 'all'
    });
    const { state: currentFilters, setState: updateFilters } = filters;

    const dataFiltered = applyFilter({
        inputData: tableData,
        comparator: getComparator(table.order, table.orderBy),
        filters: currentFilters,
    });

    const dataInPage = rowInPage(dataFiltered, table.page, table.rowsPerPage);


    const handleDeleteRow = useCallback(
        (id: string) => {
            const deleteRow = tableData.filter((row) => row.id !== id);

            toast.success('Funcionário excluído com sucesso!');

            setTableData(deleteRow);

            table.onUpdatePageDeleteRow(dataInPage.length);
        },
        [dataInPage.length, table, tableData]
    );

    const handleDeleteRows = useCallback(() => {
        const deleteRows = tableData.filter((row) => !table.selected.includes(row.id));

        toast.success('Funcionários excluídos com sucesso!');

        setTableData(deleteRows);

        table.onUpdatePageDeleteRows(dataInPage.length, dataFiltered.length);
    }, [dataFiltered.length, dataInPage.length, table, tableData]);

    const handleFilterStatus = useCallback(
        (event: React.SyntheticEvent, newValue: string) => {
            updateFilters({ status: newValue });
            table.onResetPage();
        },
        [table, updateFilters]
    );

    const statusCounts = {
        all: tableData.length,
        ativo: tableData.filter((funcionario) => funcionario.status === 'ativo').length,
        inativo: tableData.filter((funcionario) => funcionario.status === 'inativo').length,
        'ex-funcionario': tableData.filter((funcionario) => funcionario.status === 'ex-funcionario').length,
    };

    const LoadingSkeleton = () => (
        <>
            {Array.from({ length: 5 }).map((_, index) => (
                <TableRow key={index}>
                    <TableCell padding="checkbox"><Skeleton variant="rectangular" width={20} height={20} /></TableCell>
                    <TableCell><Skeleton variant="text" width="80%" /></TableCell>
                    <TableCell><Skeleton variant="text" width="60%" /></TableCell>
                    <TableCell><Skeleton variant="text" width="70%" /></TableCell>
                    <TableCell><Skeleton variant="text" width="50%" /></TableCell>
                    <TableCell><Skeleton variant="rectangular" width={60} height={24} /></TableCell>
                    <TableCell><Skeleton variant="text" width="50%" /></TableCell>
                    <TableCell><Skeleton variant="circular" width={32} height={32} /></TableCell>
                </TableRow>
            ))}
        </>
    );

    const MobileLoadingSkeleton = () => (
        <>
            {Array.from({ length: 3 }).map((_, index) => (
                <Card key={index} sx={{ p: 2, mb: 2, boxShadow: 3, borderRadius: 2 }}>
                    <Skeleton variant="text" width="80%" height={24} sx={{ mb: 1 }} />
                    <Skeleton variant="text" width="60%" height={20} sx={{ mb: 0.5 }} />
                    <Skeleton variant="text" width="70%" height={20} sx={{ mb: 0.5 }} />
                    <Skeleton variant="text" width="50%" height={20} sx={{ mb: 0.5 }} />
                    <Skeleton variant="text" width="40%" height={20} sx={{ mb: 0.5 }} />
                    <Skeleton variant="text" width="60%" height={20} sx={{ mb: 1 }} />
                    <Box sx={{ display: 'flex', justifyContent: 'flex-end', gap: 1 }}>
                        <Skeleton variant="rectangular" width={60} height={32} />
                        <Skeleton variant="rectangular" width={60} height={32} />
                    </Box>
                </Card>
            ))}
        </>
    );

    const renderConfirmDialog = () => (
        <ConfirmDialog
            open={confirmDialog.value}
            onClose={confirmDialog.onFalse}
            title="Excluir"
            content="Tem certeza que deseja excluir os funcionários selecionados?"
            action={
                <Button variant="contained" color="error" onClick={handleDeleteRows}>
                    Excluir
                </Button>
            }
        />
    );

    return (
        <>
            <DashboardContent maxWidth="xl">
                <CustomBreadcrumbs
                    heading="Lista de Funcionários"
                    links={[
                        { name: 'Dashboard', href: paths.dashboard.root },
                        { name: 'Funcionários', href: paths.dashboard.general.funcionarios },
                        { name: 'Lista' },
                    ]}
                    action={
                        <Button
                            onClick={onNewFuncionario}
                            variant="contained"
                            startIcon={<Iconify icon="mingcute:add-line" />}
                        >
                            Novo Funcionário
                        </Button>
                    }
                    sx={{ mb: { xs: 3, md: 4 } }}
                />

                <Box sx={{
                    display: 'grid',
                    gridTemplateColumns: '1fr',
                    width: '100%',
                    gap: { xs: 2, sm: 3 }
                }}>
                    <Box sx={{ width: '100%' }}>
                        <FuncionarioTableToolbar
                            filters={filters}
                            onResetPage={table.onResetPage}
                            onFilterStatus={handleFilterStatus}
                            currentStatus={currentFilters.status}
                            statusCounts={statusCounts}
                        />
                    </Box>

                    <Box sx={{ display: { xs: 'none', sm: 'block' }, width: '100%' }}>
                        <Card sx={{
                            p: { xs: 2, sm: 3 },
                            boxShadow: 3,
                            borderRadius: 2,
                            bgcolor: 'background.paper',
                            border: '1px solid',
                            borderColor: 'divider'
                        }}>
                            <TableContainer sx={{
                                borderRadius: 1,
                                overflow: 'hidden',
                                '&::-webkit-scrollbar': {
                                    height: 8,
                                },
                                '&::-webkit-scrollbar-track': {
                                    backgroundColor: 'rgba(0,0,0,0.1)',
                                },
                                '&::-webkit-scrollbar-thumb': {
                                    backgroundColor: 'rgba(0,0,0,0.3)',
                                    borderRadius: 4,
                                },
                            }}>
                                <Table size="small" sx={{ minWidth: 600 }}>
                                    <TableHead>
                                        <TableRow>
                                            <TableCell padding="checkbox" sx={{ fontWeight: 600 }} />
                                            <TableCell sx={{ fontWeight: 600 }}>NOME</TableCell>
                                            <TableCell sx={{ fontWeight: 600 }}>TELEFONE</TableCell>
                                            <TableCell sx={{ fontWeight: 600 }}>FUNÇÃO</TableCell>
                                            <TableCell sx={{ fontWeight: 600 }}>NÍVEL</TableCell>
                                            <TableCell sx={{ fontWeight: 600 }}>STATUS</TableCell>
                                            <TableCell sx={{ fontWeight: 600 }}>ANIVERSÁRIO</TableCell>
                                            <TableCell align="center" sx={{ fontWeight: 600 }}>AÇÕES</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {isLoading ? (
                                            <LoadingSkeleton />
                                        ) : dataFiltered.length === 0 ? (
                                            <TableRow>
                                                <TableCell colSpan={8} align="center" sx={{ py: 4 }}>
                                                    <Typography variant="body2" color="text.secondary">
                                                        Nenhum funcionário encontrado
                                                    </Typography>
                                                </TableCell>
                                            </TableRow>
                                        ) : (
                                            dataFiltered
                                                .slice(
                                                    table.page * table.rowsPerPage,
                                                    table.page * table.rowsPerPage + table.rowsPerPage
                                                )
                                                .map((row) => (
                                                    <FuncionarioTableRow
                                                        key={row.id}
                                                        row={row}
                                                        selected={table.selected.includes(row.id)}
                                                        onSelectRow={() => table.onSelectRow(row.id)}
                                                        onDeleteRow={() => handleDeleteRow(row.id)}
                                                        onEditRow={onEditFuncionario}
                                                        editHref={paths.dashboard.general.funcionarios}
                                                    />
                                                ))
                                        )}
                                    </TableBody>
                                </Table>
                            </TableContainer>

                            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 3 }}>
                                <TablePaginationCustom
                                    page={table.page}
                                    count={dataFiltered.length}
                                    rowsPerPage={table.rowsPerPage}
                                    onPageChange={table.onChangePage}
                                    onRowsPerPageChange={table.onChangeRowsPerPage}
                                />
                            </Box>
                        </Card>
                    </Box>

                    <Box sx={{ display: { xs: 'block', sm: 'none' }, width: '100%', gap: 2 }}>
                        {isLoading ? (
                            <MobileLoadingSkeleton />
                        ) : dataFiltered.length === 0 ? (
                            <Typography variant="body2" color="text.secondary" align="center" sx={{ py: 4 }}>
                                Nenhum funcionário encontrado
                            </Typography>
                        ) : (
                            dataFiltered
                                .slice(
                                    table.page * table.rowsPerPage,
                                    table.page * table.rowsPerPage + table.rowsPerPage
                                )
                                .map((funcionario) => (
                                    <Card key={funcionario.id} sx={{
                                        p: { xs: 2, sm: 3 },
                                        mb: 2,
                                        boxShadow: 3,
                                        borderRadius: 2,
                                        bgcolor: 'background.paper',
                                        border: '1px solid',
                                        borderColor: 'divider'
                                    }}>
                                        <Typography variant="subtitle2" fontWeight={600}>{funcionario.nome}</Typography>
                                        <Typography variant="body2">Telefone: {funcionario.telefone}</Typography>
                                        <Typography variant="body2">Função: {funcionario.funcao}</Typography>
                                        <Typography variant="body2">Nível: {funcionario.nivel}</Typography>
                                        <Typography variant="body2">Status: {funcionario.status}</Typography>
                                        <Typography variant="body2">Aniversário: {funcionario.aniversario}</Typography>
                                        <Box sx={{
                                            display: 'flex',
                                            justifyContent: 'flex-end',
                                            mt: 1,
                                            gap: { xs: 1, sm: 2 },
                                            flexWrap: 'wrap'
                                        }}>
                                            <Button
                                                size="small"
                                                variant="outlined"
                                                onClick={() => onEditFuncionario?.(funcionario)}
                                                sx={{
                                                    textTransform: 'none',
                                                    minWidth: { xs: '80px', sm: '100px' }
                                                }}
                                            >
                                                Editar
                                            </Button>
                                            <Button
                                                size="small"
                                                variant="outlined"
                                                color="error"
                                                onClick={() => handleDeleteRow(funcionario.id)}
                                                sx={{
                                                    textTransform: 'none',
                                                    minWidth: { xs: '80px', sm: '100px' }
                                                }}
                                            >
                                                Excluir
                                            </Button>
                                        </Box>
                                    </Card>
                                ))
                        )}

                        {!isLoading && (
                            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 1 }}>
                                <TablePaginationCustom
                                    page={table.page}
                                    count={dataFiltered.length}
                                    rowsPerPage={table.rowsPerPage}
                                    onPageChange={table.onChangePage}
                                    onRowsPerPageChange={table.onChangeRowsPerPage}
                                />
                            </Box>
                        )}
                    </Box>
                </Box>
            </DashboardContent>

            {renderConfirmDialog()}
        </>
    );
}

type ApplyFilterProps = {
    inputData: IFuncionarioItem[];
    comparator: (a: any, b: any) => number;
    filters: IFuncionarioTableFilters;
};

function applyFilter({ inputData, comparator, filters }: ApplyFilterProps) {
    const { name, status } = filters;

    const stabilizedThis = inputData.map((el, index) => [el, index] as const);

    stabilizedThis.sort((a, b) => {
        const order = comparator(a[0], b[0]);
        if (order !== 0) return order;
        return a[1] - b[1];
    });

    inputData = stabilizedThis.map((el) => el[0]);

    if (name) {
        inputData = inputData.filter(
            (funcionario) =>
                funcionario.nome.toLowerCase().indexOf(name.toLowerCase()) !== -1 ||
                funcionario.email?.toLowerCase().indexOf(name.toLowerCase()) !== -1
        );
    }

    if (status !== 'all') {
        inputData = inputData.filter((funcionario) => funcionario.status === status);
    }

    return inputData;
}
