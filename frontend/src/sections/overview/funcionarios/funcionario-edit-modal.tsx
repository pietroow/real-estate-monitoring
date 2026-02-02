import type { IFuncionarioItem } from 'src/types/funcionario';

import { useState, useEffect } from 'react';

import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import IconButton from '@mui/material/IconButton';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';

import { Iconify } from 'src/components/iconify';

// ----------------------------------------------------------------------

type Props = {
    open: boolean;
    onClose: () => void;
    onSave: (funcionario: IFuncionarioItem) => void;
    funcionario: IFuncionarioItem | null;
};

export function FuncionarioEditModal({ open, onClose, onSave, funcionario }: Props) {
    const [formData, setFormData] = useState<Partial<IFuncionarioItem>>({});

    useEffect(() => {
        if (funcionario) {
            setFormData(funcionario);
        }
    }, [funcionario]);

    const handleSave = () => {
        if (funcionario && formData) {
            const funcionarioAtualizado = {
                ...funcionario,
                ...formData,
            };
            onSave(funcionarioAtualizado);
            onClose();
        }
    };

    const handleChange = (field: keyof IFuncionarioItem, value: any) => {
        setFormData(prev => ({
            ...prev,
            [field]: value
        }));
    };

    if (!funcionario) return null;

    return (
        <Dialog
            open={open}
            onClose={onClose}
            maxWidth="md"
            fullWidth
            PaperProps={{
                sx: {
                    borderRadius: 2,
                    maxHeight: '90vh',
                },
            }}
        >
            <DialogTitle sx={{ pb: 2 }}>
                <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
                    <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                        <Iconify icon="solar:user-id-bold" width={24} />
                        Editar Funcionário
                    </Box>
                    <IconButton onClick={onClose} size="small">
                        <Iconify icon="solar:close-circle-bold" />
                    </IconButton>
                </Box>
            </DialogTitle>

            <DialogContent sx={{ px: 3, py: 2 }}>
                <Card sx={{ p: { xs: 2, sm: 3 } }}>
                    <Box
                        sx={{
                            rowGap: { xs: 2, sm: 3 },
                            columnGap: { xs: 1, sm: 2 },
                            display: 'grid',
                            gridTemplateColumns: { xs: 'repeat(1, 1fr)', sm: 'repeat(2, 1fr)' },
                        }}
                    >
                        {/* Nome */}
                        <Box>
                            <label style={{ display: 'block', marginBottom: 4, fontWeight: 500 }}>
                                Nome Completo
                            </label>
                            <input
                                type="text"
                                value={formData.nome || ''}
                                onChange={(e) => handleChange('nome', e.target.value)}
                                style={{
                                    width: '100%',
                                    padding: '8px 12px',
                                    border: '1px solid #ccc',
                                    borderRadius: '4px',
                                    fontSize: '14px',
                                    minHeight: '40px'
                                }}
                            />
                        </Box>

                        <Box>
                            <label style={{ display: 'block', marginBottom: 4, fontWeight: 500 }}>
                                Telefone
                            </label>
                            <input
                                type="text"
                                value={formData.telefone || ''}
                                onChange={(e) => handleChange('telefone', e.target.value)}
                                style={{
                                    width: '100%',
                                    padding: '8px 12px',
                                    border: '1px solid #ccc',
                                    borderRadius: '4px',
                                    fontSize: '14px',
                                    minHeight: '40px'
                                }}
                            />
                        </Box>

                        <Box>
                            <label style={{ display: 'block', marginBottom: 4, fontWeight: 500 }}>
                                Função
                            </label>
                            <input
                                type="text"
                                value={formData.funcao || ''}
                                onChange={(e) => handleChange('funcao', e.target.value)}
                                style={{
                                    width: '100%',
                                    padding: '8px 12px',
                                    border: '1px solid #ccc',
                                    borderRadius: '4px',
                                    fontSize: '14px',
                                    minHeight: '40px'
                                }}
                            />
                        </Box>

                        <Box>
                            <label style={{ display: 'block', marginBottom: 4, fontWeight: 500 }}>
                                Nível
                            </label>
                            <select
                                value={formData.nivel || ''}
                                onChange={(e) => handleChange('nivel', e.target.value)}
                                style={{
                                    width: '100%',
                                    padding: '8px 12px',
                                    border: '1px solid #ccc',
                                    borderRadius: '4px',
                                    fontSize: '14px',
                                    minHeight: '40px'
                                }}
                            >
                                <option value="Básico">Básico</option>
                                <option value="Intermediário">Intermediário</option>
                                <option value="Avançado">Avançado</option>
                                <option value="Especialista">Especialista</option>
                                <option value="Mestre">Mestre</option>
                            </select>
                        </Box>

                        <Box>
                            <label style={{ display: 'block', marginBottom: 4, fontWeight: 500 }}>
                                Status
                            </label>
                            <select
                                value={formData.status || 'ativo'}
                                onChange={(e) => handleChange('status', e.target.value)}
                                style={{
                                    width: '100%',
                                    padding: '8px 12px',
                                    border: '1px solid #ccc',
                                    borderRadius: '4px',
                                    fontSize: '14px',
                                    minHeight: '40px'
                                }}
                            >
                                <option value="ativo">Ativo</option>
                                <option value="inativo">Inativo</option>
                                <option value="ex-funcionario">Ex-funcionário</option>
                            </select>
                        </Box>

                        <Box>
                            <label style={{ display: 'block', marginBottom: 4, fontWeight: 500 }}>
                                Aniversário
                            </label>
                            <input
                                type="text"
                                value={formData.aniversario || ''}
                                onChange={(e) => handleChange('aniversario', e.target.value)}
                                placeholder="DD/MM/AAAA"
                                style={{
                                    width: '100%',
                                    padding: '8px 12px',
                                    border: '1px solid #ccc',
                                    borderRadius: '4px',
                                    fontSize: '14px',
                                    minHeight: '40px'
                                }}
                            />
                        </Box>

                        <Box sx={{ gridColumn: '1 / -1' }}>
                            <label style={{ display: 'block', marginBottom: 4, fontWeight: 500 }}>
                                Email
                            </label>
                            <input
                                type="email"
                                value={formData.email || ''}
                                onChange={(e) => handleChange('email', e.target.value)}
                                style={{
                                    width: '100%',
                                    padding: '8px 12px',
                                    border: '1px solid #ccc',
                                    borderRadius: '4px',
                                    fontSize: '14px',
                                    minHeight: '40px'
                                }}
                            />
                        </Box>
                    </Box>
                </Card>
            </DialogContent>

            <DialogActions sx={{ px: 3, py: 2, gap: 1 }}>
                <Button
                    onClick={onClose}
                    variant="outlined"
                >
                    Cancelar
                </Button>

                <Button
                    onClick={handleSave}
                    variant="contained"
                    color="primary"
                    startIcon={<Iconify icon="solar:check-circle-bold" />}
                >
                    Salvar Alterações
                </Button>
            </DialogActions>
        </Dialog>
    );
}
