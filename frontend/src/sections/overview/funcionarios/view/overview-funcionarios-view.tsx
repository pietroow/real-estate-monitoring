import type { IFuncionarioItem } from 'src/types/funcionario';

import { useState } from 'react';

import { FuncionarioListView } from '../funcionario-list-view';
import { FuncionarioEditModal } from '../funcionario-edit-modal';
import { FuncionarioCreateModal } from '../funcionario-create-modal';

export function OverviewFuncionariosView() {
    const [showCreateModal, setShowCreateModal] = useState(false);
    const [showEditModal, setShowEditModal] = useState(false);
    const [funcionarioToEdit, setFuncionarioToEdit] = useState<IFuncionarioItem | null>(null);

    const handleOpenCreateModal = () => {
        setShowCreateModal(true);
    };

    const handleCloseCreateModal = () => {
        setShowCreateModal(false);
    };

    const handleOpenEditModal = (funcionario: IFuncionarioItem) => {
        setFuncionarioToEdit(funcionario);
        setShowEditModal(true);
    };

    const handleCloseEditModal = () => {
        setShowEditModal(false);
        setFuncionarioToEdit(null);
    };

    const handleCreateSuccess = (novoFuncionario?: IFuncionarioItem) => {
        console.log('handleCreateSuccess chamado!');
        handleCloseCreateModal();
    };

    const handleEditSuccess = (funcionarioAtualizado: IFuncionarioItem) => {
        console.log('handleEditSuccess chamado!', funcionarioAtualizado);
        // Disparar evento para atualizar a lista
        window.dispatchEvent(new CustomEvent('funcionarioUpdated', {
            detail: funcionarioAtualizado
        }));
        handleCloseEditModal();
    };

    return (
        <>
            <FuncionarioListView
                onNewFuncionario={handleOpenCreateModal}
                onEditFuncionario={handleOpenEditModal}
            />
            <FuncionarioCreateModal
                open={showCreateModal}
                onClose={handleCloseCreateModal}
                onSuccess={handleCreateSuccess}
            />
            <FuncionarioEditModal
                open={showEditModal}
                onClose={handleCloseEditModal}
                onSave={handleEditSuccess}
                funcionario={funcionarioToEdit}
            />
        </>
    );
}
