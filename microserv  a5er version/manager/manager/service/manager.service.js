const { Manager } = require('../models/Manager');

// Assigner une équipe à un manager
async function assignTeamToManager(managerId, teamId) {
    try {
        const manager = await Manager.findByIdAndUpdate(
            managerId,
            { equipe: teamId },
            { new: true }
        );
        
        if (!manager) {
            throw new Error('Manager not found');
        }
        
        return manager;
    } catch (error) {
        throw error;
    }
}

// Trouver un manager par équipe
async function getManagerByTeamId(teamId) {
    return Manager.findOne({ equipe: teamId });
}

// Vérifier si un manager existe
async function managerExists(managerId) {
    const manager = await Manager.findById(managerId);
    return !!manager;
}

module.exports = {
    assignTeamToManager,
    getManagerByTeamId,
    managerExists
};