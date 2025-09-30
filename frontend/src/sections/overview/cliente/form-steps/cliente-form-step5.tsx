import type { IClienteItem } from 'src/types/cliente';

import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import { alpha } from '@mui/material/styles';
import Typography from '@mui/material/Typography';

import { Iconify } from 'src/components/iconify';

type Props = {
  data: Partial<IClienteItem>;
  onChange: (data: Partial<IClienteItem>) => void;
  errors?: Record<string, boolean>;
};

export function ClienteFormStep5({ data, onChange }: Props) {
  const handleChange = (field: string, value: any) => {
    onChange({ [field]: value } as any);
  };

  return (
    <Box>
      <Typography variant="h6" sx={{ mb: 3, color: 'text.primary' }}>
        Arquivos
      </Typography>

      <Paper
        variant="outlined"
        sx={{
          p: 6,
          textAlign: 'center',
          bgcolor: alpha('#919EAB', 0.04),
          border: `2px dashed ${alpha('#919EAB', 0.32)}`,
          borderRadius: 2,
          cursor: 'pointer',
          transition: 'all 0.3s ease',
          '&:hover': {
            bgcolor: alpha('#919EAB', 0.08),
            borderColor: 'primary.main',
          },
        }}
      >
        <Box sx={{ mb: 2 }}>
          <Iconify
            icon="eva:cloud-upload-fill"
            sx={{
              width: 48,
              height: 48,
              color: 'text.disabled',
            }}
          />
        </Box>

        <Typography variant="h6" sx={{ mb: 1, color: 'text.primary' }}>
          Clique ou arraste aqui
        </Typography>

        <Typography variant="body2" sx={{ color: 'text.secondary' }}>
          Adicione documentos, contratos ou outros arquivos relacionados ao cliente
        </Typography>

        <Typography variant="caption" sx={{ mt: 2, display: 'block', color: 'text.disabled' }}>
          Formatos aceitos: PDF, DOC, DOCX, XLS, XLSX, JPG, PNG
        </Typography>

        <Typography variant="caption" sx={{ color: 'text.disabled' }}>
          Tamanho máximo: 10MB por arquivo
        </Typography>

        <input
          type="file"
          multiple
          accept=".pdf,.doc,.docx,.xls,.xlsx,.jpg,.jpeg,.png"
          style={{ display: 'none' }}
          onChange={(e) => {
            const files = Array.from(e.target.files || []);
            handleChange('arquivos', files);
          }}
          id="file-upload"
        />
        <label htmlFor="file-upload" style={{ cursor: 'pointer' }}>
          <Box
            component="span"
            sx={{
              mt: 3,
              display: 'inline-block',
              px: 3,
              py: 1,
              bgcolor: 'primary.main',
              color: 'white',
              borderRadius: 1,
              fontSize: '0.875rem',
              fontWeight: 'medium',
              '&:hover': {
                bgcolor: 'primary.dark',
              },
            }}
          >
            Selecionar Arquivos
          </Box>
        </label>

        {data.arquivos && Array.isArray(data.arquivos) && data.arquivos.length > 0 && (
          <Box sx={{ mt: 3, textAlign: 'left' }}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Arquivos selecionados:
            </Typography>
            {data.arquivos.map((file: File, index: number) => (
              <Typography
                key={index}
                variant="body2"
                sx={{
                  display: 'flex',
                  alignItems: 'center',
                  gap: 1,
                  py: 0.5,
                  color: 'text.secondary',
                }}
              >
                <Iconify icon="solar:file-text-bold" width={16} />
                {file.name} ({Math.round(file.size / 1024)}KB)
              </Typography>
            ))}
          </Box>
        )}
      </Paper>
    </Box>
  );
}