import { z } from 'zod';
import React from 'react';
import dayjs from 'dayjs';
import { useForm, Controller } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { isValidPhoneNumber } from 'react-phone-number-input/input';

import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

import { Form, Field, schemaUtils } from 'src/components/hook-form';

const PersonalDataSchema = z.object({
  nome: z.string().min(1, { message: 'Nome é obrigatório!' }),
  sexo: z.enum(['Masculino', 'Feminino'], { message: 'Sexo é obrigatório!' }),
  cpf: z.string()
    .min(14, { message: 'CPF é obrigatório!' })
    .regex(/^\d{3}\.\d{3}\.\d{3}-\d{2}$/, { message: 'CPF deve ter formato 000.000.000-00!' }),
  rg: z.string()
    .min(12, { message: 'RG é obrigatório!' })
    .regex(/^\d{2}\.\d{3}\.\d{3}-\d{1}$/, { message: 'RG deve ter formato 00.000.000-0!' }),
  telefone1: schemaUtils.phoneNumber({ isValid: isValidPhoneNumber }),
  telefone2: schemaUtils.phoneNumber({ isValid: isValidPhoneNumber }).optional().nullable(),
  email: schemaUtils.email(),
  dataNascimento: z.any(),
  nomeMae: z.string().min(1, { message: 'Nome da mãe é obrigatório!' }),
  nomePai: z.string().min(1, { message: 'Nome do pai é obrigatório!' }),
});

type PersonalDataFormValues = z.infer<typeof PersonalDataSchema>;

type Props = {
  data: any;
  onChange: (data: any) => void;
};

export function FuncionarioPersonalDataStep({ data, onChange }: Props) {
  const defaultValues: PersonalDataFormValues = {
    nome: data.nome || '',
    sexo: data.sexo || 'Masculino',
    cpf: data.cpf || '',
    rg: data.rg || '',
    telefone1: data.telefone1 || '',
    telefone2: data.telefone2 || '',
    email: data.email || '',
    dataNascimento: data.dataNascimento ? dayjs(data.dataNascimento) : dayjs(),
    nomeMae: data.nomeMae || '',
    nomePai: data.nomePai || '',
  };

  const formatCPF = (value: string) => {
    const numbers = value.replace(/\D/g, '');
    return numbers.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  };

  const formatRG = (value: string) => {
    const numbers = value.replace(/\D/g, '');
    return numbers.replace(/(\d{2})(\d{3})(\d{3})(\d{1})/, '$1.$2.$3-$4');
  };

  const methods = useForm<PersonalDataFormValues>({
    resolver: zodResolver(PersonalDataSchema),
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
        Dados Pessoais
      </Typography>

      <Box
        sx={{
          rowGap: 3,
          columnGap: 2,
          display: 'grid',
          gridTemplateColumns: { xs: 'repeat(1, 1fr)', sm: 'repeat(2, 1fr)' },
        }}
      >
        <Field.Text name="nome" label="Nome Completo" />

        <Field.RadioGroup
          name="sexo"
          options={[
            { label: 'Masculino', value: 'Masculino' },
            { label: 'Feminino', value: 'Feminino' }
          ]}
          label="Sexo"
          row
        />

        <Controller
          name="cpf"
          control={control}
          render={({ field, fieldState: { error } }) => (
            <Field.Text
              {...field}
              label="CPF"
              placeholder="000.000.000-00"
              error={!!error}
              helperText={error?.message}
              onChange={(e) => {
                const formatted = formatCPF(e.target.value);
                field.onChange(formatted);
              }}
              inputProps={{ maxLength: 14 }}
            />
          )}
        />
        <Controller
          name="rg"
          control={control}
          render={({ field, fieldState: { error } }) => (
            <Field.Text
              {...field}
              label="RG"
              placeholder="00.000.000-0"
              error={!!error}
              helperText={error?.message}
              onChange={(e) => {
                const formatted = formatRG(e.target.value);
                field.onChange(formatted);
              }}
              inputProps={{ maxLength: 12 }}
            />
          )}
        />

        <Field.Phone name="telefone1" label="Telefone 1" defaultCountry="BR" />
        <Field.Phone name="telefone2" label="Telefone 2" defaultCountry="BR" />

        <Field.Text name="email" label="Endereço de E-mail" required />

        <Controller
          name="dataNascimento"
          control={control}
          render={({ field, fieldState: { error } }) => (
            <DatePicker
              {...field}
              label="Data de Nascimento"
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

        <Field.Text name="nomeMae" label="Nome da Mãe" />
        <Field.Text name="nomePai" label="Nome do Pai" />
      </Box>
    </Form>
  );
}
