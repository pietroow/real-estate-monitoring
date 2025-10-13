import { useState } from 'react';

import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import Step from '@mui/material/Step';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import Stepper from '@mui/material/Stepper';
import StepLabel from '@mui/material/StepLabel';
import IconButton from '@mui/material/IconButton';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';

import { Iconify } from 'src/components/iconify';

import { FuncionarioAddressStep } from './steps/funcionario-address-step';
import { FuncionarioReferencesStep } from './steps/funcionario-references-step';
import { FuncionarioProfessionalStep } from './steps/funcionario-professional-step';
import { FuncionarioPersonalDataStep } from './steps/funcionario-personal-data-step';

// ----------------------------------------------------------------------

const steps = [
  'Dados Pessoais',
  'Endereço',
  'Informações Profissionais',
  'Referências',
];

type Props = {
  open: boolean;
  onClose: () => void;
  onSuccess?: () => void;
};

export function FuncionarioCreateModal({ open, onClose, onSuccess }: Props) {
  const [activeStep, setActiveStep] = useState(0);
  const [formData, setFormData] = useState<any>({
    status: 'Ativo',
    regimeTrabalho: 'CLT',
    perfil: 'Operacional',
    nivel: 'Básico',
    uf: 'SP',
    sexo: 'Masculino'
  });

  const validateCurrentStep = () => {
    // Para o último step (Referências), sempre permitir avançar
    if (activeStep === 3) {
      return true;
    }

    // Para os outros steps, validar campos básicos
    const isValid = (() => {
      switch (activeStep) {
        case 0: // Dados Pessoais - apenas nome obrigatório
          return formData.nome;
        case 1: // Endereço - apenas endereço obrigatório
          return formData.endereco;
        case 2: // Informações Profissionais - apenas função obrigatória
          return formData.funcao;
        default:
          return true;
      }
    })();

    console.log(`Validação do step ${activeStep}:`, isValid, 'Dados:', formData);
    return isValid;
  };

  const handleNext = () => {
    if (validateCurrentStep()) {
      setActiveStep((prevActiveStep) => prevActiveStep + 1);
    }
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleReset = () => {
    setActiveStep(0);
    setFormData({});
  };

  const handleClose = () => {
    handleReset();
    onClose();
  };

  const handleFinish = () => {
    console.log('handleFinish chamado! Dados do formulário:', formData);
    console.log('Status atual:', formData.status);

    // Criar funcionário com os dados do formulário
    const novoFuncionario = {
      id: Math.random().toString(36).substr(2, 9),
      nome: formData.nome || 'Funcionário',
      email: formData.email || 'email@exemplo.com',
      telefone: formData.telefone1 || '(11) 99999-9999',
      cpf: formData.cpf || '000.000.000-00',
      rg: formData.rg || '00.000.000-0',
      funcao: formData.funcao || 'Função',
      nivel: formData.nivel || 'Nível',
      status: (formData.status === 'Ativo' ? 'ativo' :
        formData.status === 'Inativo' ? 'inativo' :
          formData.status === 'Ex-funcionário' ? 'ex-funcionario' : 'ativo') as 'ativo' | 'inativo' | 'ex-funcionario',
      aniversario: formData.dataNascimento ?
        (typeof formData.dataNascimento === 'string' ? formData.dataNascimento :
          formData.dataNascimento.format ? formData.dataNascimento.format('DD/MM/YYYY') : '01/01/1990') :
        '01/01/1990',
      avatarUrl: `https://i.pravatar.cc/150?img=${Math.floor(Math.random() * 50) + 1}`,
      dataAdmissao: formData.dataAdmissao ?
        (typeof formData.dataAdmissao === 'string' ? formData.dataAdmissao :
          formData.dataAdmissao.format ? formData.dataAdmissao.format('DD/MM/YYYY') : '01/01/2024') :
        '01/01/2024',
      dataDemissao: formData.dataDemissao ?
        (typeof formData.dataDemissao === 'string' ? formData.dataDemissao :
          formData.dataDemissao.format ? formData.dataDemissao.format('DD/MM/YYYY') : undefined) :
        undefined,
      empresa: 'Empresa Atual',
      endereco: formData.endereco || 'Endereço',
      cidade: formData.cidade || 'Cidade',
      estado: formData.estado || 'Estado',
      cep: formData.cep || '00000-000',
      sexo: (formData.sexo === 'Masculino' ? 'masculino' : 'feminino') as 'masculino' | 'feminino',
      dataNascimento: formData.dataNascimento ?
        (typeof formData.dataNascimento === 'string' ? formData.dataNascimento :
          formData.dataNascimento.format ? formData.dataNascimento.format('DD/MM/YYYY') : '01/01/1990') :
        '01/01/1990',
      quantidadeFilhos: 0,
      nomeMae: formData.nomeMae || 'Nome da Mãe',
      nomePai: formData.nomePai || 'Nome do Pai',
      regimeTrabalho: formData.regimeTrabalho || 'Regime',
      perfil: formData.perfil || 'Perfil',
      observacoes: formData.observacoes || '',
      comentario: formData.comentario || '',
    };

    console.log('Novo funcionário criado:', novoFuncionario);

    // Disparar evento para adicionar à lista
    const event = new CustomEvent('funcionarioCreated', {
      detail: novoFuncionario
    });
    console.log('Disparando evento:', event);
    window.dispatchEvent(event);

    // Chamar callback de sucesso
    onSuccess?.();
    handleClose();
  };

  const renderStepContent = (step: number) => {
    switch (step) {
      case 0:
        return (
          <FuncionarioPersonalDataStep
            data={formData}
            onChange={setFormData}
          />
        );
      case 1:
        return (
          <FuncionarioAddressStep
            data={formData}
            onChange={setFormData}
          />
        );
      case 2:
        return (
          <FuncionarioProfessionalStep
            data={formData}
            onChange={setFormData}
          />
        );
      case 3:
        return (
          <FuncionarioReferencesStep
            data={formData}
            onChange={setFormData}
          />
        );
      default:
        return null;
    }
  };

  return (
    <Dialog
      open={open}
      onClose={handleClose}
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
            <Iconify icon="solar:user-plus-bold" width={24} />
            Cadastro de Funcionário
          </Box>
          <IconButton onClick={handleClose} size="small">
            <Iconify icon="solar:close-circle-bold" />
          </IconButton>
        </Box>
      </DialogTitle>

      <DialogContent sx={{ px: 3, py: 2 }}>
        {/* Stepper */}
        <Box sx={{ mb: 4 }}>
          <Stepper activeStep={activeStep} alternativeLabel>
            {steps.map((label) => (
              <Step key={label}>
                <StepLabel>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>
        </Box>

        {/* Step Content */}
        <Card sx={{ p: 3, minHeight: 400 }}>
          {renderStepContent(activeStep)}
        </Card>
      </DialogContent>

      <DialogActions sx={{ px: 3, py: 2, gap: 1 }}>
        <Button
          onClick={handleBack}
          disabled={activeStep === 0}
          variant="outlined"
        >
          Voltar
        </Button>

        <Box sx={{ flex: '1 1 auto' }} />

        {activeStep === steps.length - 1 ? (
          <Button
            onClick={handleFinish}
            variant="contained"
            color="success"
            startIcon={<Iconify icon="solar:check-circle-bold" />}
          >
            Finalizar
          </Button>
        ) : (
          <Button
            onClick={handleNext}
            variant="contained"
            disabled={!validateCurrentStep()}
            endIcon={<Iconify icon="eva:arrowhead-right-fill" />}
          >
            Próximo
          </Button>
        )}
      </DialogActions>
    </Dialog>
  );
}
