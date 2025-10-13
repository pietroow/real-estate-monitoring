import { z } from 'zod';
import React from 'react';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { isValidPhoneNumber } from 'react-phone-number-input/input';

import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';

import { Form, Field, schemaUtils } from 'src/components/hook-form';

const ReferencesSchema = z.object({
    referencia1Empresa: z.string().optional().nullable(),
    referencia1Contato: z.string().optional().nullable(),
    referencia1Telefone: schemaUtils.phoneNumber({ isValid: isValidPhoneNumber }).optional().nullable(),
    referencia2Empresa: z.string().optional().nullable(),
    referencia2Contato: z.string().optional().nullable(),
    referencia2Telefone: schemaUtils.phoneNumber({ isValid: isValidPhoneNumber }).optional().nullable(),
});

type ReferencesFormValues = z.infer<typeof ReferencesSchema>;

type Props = {
    data: any;
    onChange: (data: any) => void;
};

export function FuncionarioReferencesStep({ data, onChange }: Props) {
    const defaultValues: ReferencesFormValues = {
        referencia1Empresa: data.referencia1Empresa || '',
        referencia1Contato: data.referencia1Contato || '',
        referencia1Telefone: data.referencia1Telefone || '',
        referencia2Empresa: data.referencia2Empresa || '',
        referencia2Contato: data.referencia2Contato || '',
        referencia2Telefone: data.referencia2Telefone || '',
    };

    const methods = useForm<ReferencesFormValues>({
        resolver: zodResolver(ReferencesSchema),
        defaultValues,
        mode: 'onChange',
    });

    const { watch } = methods;

    const watchedValues = watch();

    React.useEffect(() => {
        onChange({ ...data, ...watchedValues });
    }, [watchedValues, data, onChange]);

    return (
        <Form methods={methods}>
            <Typography variant="h6" sx={{ mb: 3, color: 'primary.main' }}>
                Referências
            </Typography>

            {/* Referência 1 */}
            <Box sx={{ mb: 4 }}>
                <Typography variant="subtitle1" sx={{ mb: 2, fontWeight: 600 }}>
                    Referência 1
                </Typography>
                <Box
                    sx={{
                        rowGap: 3,
                        columnGap: 2,
                        display: 'grid',
                        gridTemplateColumns: { xs: 'repeat(1, 1fr)', sm: 'repeat(2, 1fr)' },
                    }}
                >
                    <Field.Text name="referencia1Empresa" label="Empresa" />
                    <Field.Text name="referencia1Contato" label="Contato" />
                    <Field.Phone
                        name="referencia1Telefone"
                        label="Telefone"
                        defaultCountry="BR"
                        sx={{ gridColumn: { xs: '1 / -1', sm: '1 / 2' } }}
                    />
                </Box>
            </Box>

            {/* Referência 2 */}
            <Box>
                <Typography variant="subtitle1" sx={{ mb: 2, fontWeight: 600 }}>
                    Referência 2
                </Typography>
                <Box
                    sx={{
                        rowGap: 3,
                        columnGap: 2,
                        display: 'grid',
                        gridTemplateColumns: { xs: 'repeat(1, 1fr)', sm: 'repeat(2, 1fr)' },
                    }}
                >
                    <Field.Text name="referencia2Empresa" label="Empresa" />
                    <Field.Text name="referencia2Contato" label="Contato" />
                    <Field.Phone
                        name="referencia2Telefone"
                        label="Telefone"
                        defaultCountry="BR"
                        sx={{ gridColumn: { xs: '1 / -1', sm: '1 / 2' } }}
                    />
                </Box>
            </Box>
        </Form>
    );
}
