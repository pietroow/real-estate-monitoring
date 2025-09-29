import { z } from 'zod';
import React from 'react';
import dayjs from 'dayjs';
import { useForm, Controller } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';

import Box from '@mui/material/Box';
import MenuItem from '@mui/material/MenuItem';
import Typography from '@mui/material/Typography';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

import { Form, Field } from 'src/components/hook-form';

const ProfessionalSchema = z.object({
    status: z.string().min(1, { message: 'Status é obrigatório!' }),
    uf: z.string().min(1, { message: 'UF é obrigatória!' }),
    regimeTrabalho: z.string().min(1, { message: 'Regime de trabalho é obrigatório!' }),
    funcao: z.string().min(1, { message: 'Função é obrigatória!' }),
    dataAdmissao: z.any(),
    perfil: z.string().min(1, { message: 'Perfil é obrigatório!' }),
    nivel: z.string().min(1, { message: 'Nível é obrigatório!' }),
    dataDemissao: z.any().optional().nullable(),
    observacoes: z.string().optional().nullable(),
});

type ProfessionalFormValues = z.infer<typeof ProfessionalSchema>;

type Props = {
    data: any;
    onChange: (data: any) => void;
};

export function FuncionarioProfessionalStep({ data, onChange }: Props) {
    const defaultValues: ProfessionalFormValues = {
        status: data.status || 'Ativo',
        uf: data.uf || '',
        regimeTrabalho: data.regimeTrabalho || '',
        funcao: data.funcao || '',
        dataAdmissao: data.dataAdmissao ? dayjs(data.dataAdmissao) : dayjs(),
        perfil: data.perfil || '',
        nivel: data.nivel || '',
        dataDemissao: data.dataDemissao ? dayjs(data.dataDemissao) : null,
        observacoes: data.observacoes || '',
    };

    const methods = useForm<ProfessionalFormValues>({
        resolver: zodResolver(ProfessionalSchema),
        defaultValues,
        mode: 'onChange',
    });

    const { control, watch } = methods;

    const watchedValues = watch();

    React.useEffect(() => {
        onChange({ ...data, ...watchedValues });
    }, [watchedValues, data, onChange]);

    return (
        <Form methods={methods}>
            <Typography variant="h6" sx={{ mb: 3, color: 'primary.main' }}>
                Informações Profissionais
            </Typography>

            <Box
                sx={{
                    rowGap: 3,
                    columnGap: 2,
                    display: 'grid',
                    gridTemplateColumns: { xs: 'repeat(1, 1fr)', sm: 'repeat(2, 1fr)' },
                }}
            >
                <Field.Select name="status" label="Status">
                    <MenuItem value="Ativo">Ativo</MenuItem>
                    <MenuItem value="Inativo">Inativo</MenuItem>
                    <MenuItem value="Ex-funcionário">Ex-funcionário</MenuItem>
                </Field.Select>
                <Field.Text name="uf" label="UF" />

                <Field.Select name="regimeTrabalho" label="Regime de Trabalho">
                    <MenuItem value="CLT">CLT</MenuItem>
                    <MenuItem value="PJ">PJ</MenuItem>
                    <MenuItem value="Terceirizado">Terceirizado</MenuItem>
                    <MenuItem value="Estagiário">Estagiário</MenuItem>
                </Field.Select>
                <Field.Text name="funcao" label="Função" />

                <Controller
                    name="dataAdmissao"
                    control={control}
                    render={({ field, fieldState: { error } }) => (
                        <DatePicker
                            {...field}
                            label="Data de Admissão"
                            format="DD/MM/YYYY"
                            slotProps={{
                                textField: {
                                    fullWidth: true,
                                    error: !!error,
                                    helperText: error?.message,
                                    placeholder: "DD/MM/AAAA",
                                },
                            }}
                        />
                    )}
                />
                <Field.Select name="perfil" label="Perfil">
                    <MenuItem value="Operacional">Operacional</MenuItem>
                    <MenuItem value="Supervisão">Supervisão</MenuItem>
                    <MenuItem value="Gerencial">Gerencial</MenuItem>
                    <MenuItem value="Diretoria">Diretoria</MenuItem>
                </Field.Select>

                <Field.Select name="nivel" label="Nível">
                    <MenuItem value="Básico">Básico</MenuItem>
                    <MenuItem value="Intermediário">Intermediário</MenuItem>
                    <MenuItem value="Avançado">Avançado</MenuItem>
                    <MenuItem value="Especialista">Especialista</MenuItem>
                    <MenuItem value="Mestre">Mestre</MenuItem>
                </Field.Select>
                <Controller
                    name="dataDemissao"
                    control={control}
                    render={({ field, fieldState: { error } }) => (
                        <DatePicker
                            {...field}
                            label="Data de Demissão"
                            format="DD/MM/YYYY"
                            slotProps={{
                                textField: {
                                    fullWidth: true,
                                    error: !!error,
                                    helperText: error?.message,
                                    placeholder: "DD/MM/AAAA",
                                },
                            }}
                        />
                    )}
                />

                <Field.Text
                    name="observacoes"
                    label="Observações"
                    multiline
                    rows={3}
                    sx={{ gridColumn: '1 / -1' }}
                />
            </Box>
        </Form>
    );
}
