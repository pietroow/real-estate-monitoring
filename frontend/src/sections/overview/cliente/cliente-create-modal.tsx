import type { IClienteItem } from 'src/types/cliente';

import { useState, useEffect } from 'react';

import Box from '@mui/material/Box';
import Step from '@mui/material/Step';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import Stepper from '@mui/material/Stepper';
import StepLabel from '@mui/material/StepLabel';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';

import { Iconify } from 'src/components/iconify';

import { ClienteFormStep1 } from './form-steps/cliente-form-step1';
import { ClienteFormStep2 } from './form-steps/cliente-form-step2';
import { ClienteFormStep3 } from './form-steps/cliente-form-step3';
import { ClienteFormStep4 } from './form-steps/cliente-form-step4';
import { ClienteFormStep5 } from './form-steps/cliente-form-step5';
import { ClienteFormStep6 } from './form-steps/cliente-form-step6';


const STEPS = [
  'Dados Principais',
  'Endereço',
  'Informações Complementares',
  'Contatos',
  'Arquivos',
  'Observações',
];

type Props = {
  open: boolean;
  onClose: () => void;
  onSave: (cliente: Partial<IClienteItem>) => void;
  editingCliente?: IClienteItem | null;
};

export function ClienteCreateModal({ open, onClose, onSave, editingCliente }: Props) {
  const [activeStep, setActiveStep] = useState(0);
  const [errors, setErrors] = useState<Record<string, boolean>>({});
  const [formData, setFormData] = useState<Partial<IClienteItem>>({
    nome: '',
    setor: '',
    contato: '',
    telefone: '',
    email: '',
    fonte: '',
    status: 'ativo',
  });

  useEffect(() => {
    if (editingCliente) {
      setFormData(editingCliente);
    } else {
      setFormData({
        nome: '',
        setor: '',
        contato: '',
        telefone: '',
        email: '',
        fonte: '',
        status: 'ativo',
      });
    }
    setActiveStep(0);
    setErrors({});
  }, [editingCliente, open]);

  const validateStep = (step: number): { isValid: boolean; errors: Record<string, boolean> } => {
    const newErrors: Record<string, boolean> = {};

    switch (step) {
      case 0:
        if (!formData.nome?.trim()) newErrors.nome = true;
        if (!formData.telefone?.trim()) newErrors.telefone = true;
        if (!formData.email?.trim()) newErrors.email = true;
        break;

      case 1: 
        break;

      case 2: 
        if (!formData.fonte?.trim()) newErrors.fonte = true;
        if (!formData.setor?.trim()) newErrors.setor = true;
        break;

      case 3: 
        if (!formData.contato1Nome?.trim()) newErrors.contato1Nome = true;
        break;

      case 4: 
        break;

      case 5: 
        break;

      default:
        break;
    }

    return {
      isValid: Object.keys(newErrors).length === 0,
      errors: newErrors
    };
  };

  const handleNext = () => {
    const validation = validateStep(activeStep);
    if (validation.isValid) {
      setErrors({});
      setActiveStep((prevActiveStep) => prevActiveStep + 1);
    } else {
      setErrors(validation.errors);
    }
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleReset = () => {
    setActiveStep(0);
    setFormData({
      nome: '',
      setor: '',
      contato: '',
      telefone: '',
      email: '',
      fonte: '',
      status: 'ativo',
    });
  };

  const handleSave = () => {
    const validation = validateStep(activeStep);
    if (validation.isValid) {
      setErrors({});
      const dataToSave = {
        ...formData,
        contato: formData.contato1Nome || formData.contato || ''
      };
      onSave(dataToSave);
      handleReset();
      onClose();
    } else {
      setErrors(validation.errors);
    }
  };

  const handleClose = () => {
    handleReset();
    onClose();
  };

  const updateFormData = (stepData: Partial<IClienteItem>) => {
    setFormData(prev => ({ ...prev, ...stepData }));
  };

  const renderStepContent = (step: number) => {
    switch (step) {
      case 0:
        return <ClienteFormStep1 data={formData} onChange={updateFormData} errors={errors} />;
      case 1:
        return <ClienteFormStep2 data={formData} onChange={updateFormData} errors={errors} />;
      case 2:
        return <ClienteFormStep3 data={formData} onChange={updateFormData} errors={errors} />;
      case 3:
        return <ClienteFormStep4 data={formData} onChange={updateFormData} errors={errors} />;
      case 4:
        return <ClienteFormStep5 data={formData} onChange={updateFormData} errors={errors} />;
      case 5:
        return <ClienteFormStep6 data={formData} onChange={updateFormData} errors={errors} />;
      default:
        return null;
    }
  };

  const isLastStep = activeStep === STEPS.length - 1;

  return (
    <Dialog
      open={open}
      onClose={handleClose}
      maxWidth="md"
      fullWidth
      PaperProps={{
        sx: {
          borderRadius: 2,
          width:800,
          height:800,
          maxWidth:'100vh',
        }
      }}
    >
      <DialogTitle sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', pb: 2 }}>
        <Typography variant="h5" sx={{ fontWeight: 'medium' }}>
          {editingCliente ? 'Editar Cliente' : 'Formulário de Cadastro de Cliente'}
        </Typography>
        <Box sx={{ display: 'flex', gap: 1 }}>
          <IconButton
            onClick={handleClose}
            color="black"
            disableRipple
            sx={{
              '&:hover': {
                backgroundColor: 'transparent',
              },
              '&:focus': {
                backgroundColor: 'transparent',
              },
            }}
          >
            <Iconify icon="solar:close-circle-bold" />
          </IconButton>
        </Box>
      </DialogTitle>

      <DialogContent sx={{ 
        px: 3
      }}>
        <Box sx={{ mb: 4 }}>
          <Stepper activeStep={activeStep} alternativeLabel>
            {STEPS.map((label) => (
              <Step key={label}>
              <StepLabel>{label}</StepLabel>
            </Step>
            ))}
          </Stepper>
        </Box>


        <Box sx={{ minHeight: 200 }}>
          {renderStepContent(activeStep)}
        </Box>
      </DialogContent>

      <DialogActions sx={{ px: 3, pb: 2, pt: 1, justifyContent: 'space-between' }}>
        <Button
          disabled={activeStep === 0}
          onClick={handleBack}
          variant="outlined"
          color="inherit"
          sx={{ minWidth: 100 }}
        >
          Anterior
        </Button>

        <Button
          onClick={isLastStep ? handleSave : handleNext}
          variant="contained"
          color="primary"
          sx={{ minWidth: 100 }}
        >
          {isLastStep ? 'Salvar' : 'Próximo'}
        </Button>
      </DialogActions>
    </Dialog>
  );
}