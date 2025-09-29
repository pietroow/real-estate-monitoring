import { z } from 'zod';
import React from 'react';
import { useForm, Controller } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';

import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';

import { Form, Field } from 'src/components/hook-form';

const AddressSchema = z.object({
    cep: z.string()
        .min(9, { message: 'CEP é obrigatório!' })
        .regex(/^\d{5}-\d{3}$/, { message: 'CEP deve ter formato 00000-000!' }),
    endereco: z.string().min(1, { message: 'Endereço é obrigatório!' }),
    numero: z.number().min(1, { message: 'Número é obrigatório!' }),
    bairro: z.string().min(1, { message: 'Bairro é obrigatório!' }),
    complemento: z.string().optional().nullable(),
    estado: z.string().min(1, { message: 'Estado é obrigatório!' }),
    cidade: z.string().min(1, { message: 'Cidade é obrigatória!' }),
    comentario: z.string().optional().nullable(),
});

type AddressFormValues = z.infer<typeof AddressSchema>;

type Props = {
    data: any;
    onChange: (data: any) => void;
};

export function FuncionarioAddressStep({ data, onChange }: Props) {
    const defaultValues: AddressFormValues = {
        cep: data.cep || '',
        endereco: data.endereco || '',
        numero: data.numero || 0,
        bairro: data.bairro || '',
        complemento: data.complemento || '',
        estado: data.estado || '',
        cidade: data.cidade || '',
        comentario: data.comentario || '',
    };

    const formatCEP = (value: string) => {
        const numbers = value.replace(/\D/g, '');
        return numbers.replace(/(\d{5})(\d{3})/, '$1-$2');
    };

    const methods = useForm<AddressFormValues>({
        resolver: zodResolver(AddressSchema),
        defaultValues,
        mode: 'onChange',
    });

    const { watch, control } = methods;

    const watchedValues = watch();

    React.useEffect(() => {
        onChange({ ...data, ...watchedValues });
    }, [watchedValues, data, onChange]);

    return (
        <Form methods={methods}>
            <Typography variant="h6" sx={{ mb: 3, color: 'primary.main' }}>
                Endereço
            </Typography>

            <Box
                sx={{
                    rowGap: 3,
                    columnGap: 2,
                    display: 'grid',
                    gridTemplateColumns: { xs: 'repeat(1, 1fr)', sm: 'repeat(2, 1fr)' },
                }}
            >
                <Controller
                    name="cep"
                    control={control}
                    render={({ field, fieldState: { error } }) => (
                        <Field.Text
                            {...field}
                            label="CEP"
                            placeholder="00000-000"
                            error={!!error}
                            helperText={error?.message}
                            onChange={(e) => {
                                const formatted = formatCEP(e.target.value);
                                field.onChange(formatted);
                            }}
                            inputProps={{ maxLength: 9 }}
                        />
                    )}
                />
                <Field.Text name="endereco" label="Endereço" />
                <Field.Text name="numero" label="Número" type="number" />
                <Field.Text name="bairro" label="Bairro" />
                <Field.Text name="complemento" label="Complemento" />
                <Field.Text name="estado" label="Estado" />
                <Field.Text name="cidade" label="Cidade" />
                <Field.Text
                    name="comentario"
                    label="Comentário"
                    multiline
                    rows={3}
                    sx={{ gridColumn: '1 / -1' }}
                />
            </Box>
        </Form>
    );
}
