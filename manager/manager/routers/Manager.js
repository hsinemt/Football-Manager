const express = require('express');
const router = express.Router();
const { Manager, managerValidationSchema } = require('../models/Manager');
const managerService = require('../service/manager.service');

// Middleware de validation avec yup
const validate = (schema) => async (req, res, next) => {
    try {
        await schema.validate({ body: req.body });
        next();
    } catch (err) {
        res.status(400).json({ message: err.errors });
    }
};

// CREATE
router.post('/', validate(managerValidationSchema), async (req, res) => {
    try {
        const manager = new Manager(req.body);
        await manager.save();
        res.status(201).json(manager);
    } catch (err) {
        res.status(400).json({ message: err.message });
    }
});

// READ - Tous les managers
router.get('/', async (req, res) => {
    try {
        const managers = await Manager.find();
        res.json(managers);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// READ - Un manager par ID
router.get('/:id', async (req, res) => {
    try {
        const manager = await Manager.findById(req.params.id);
        if (!manager) return res.status(404).json({ message: 'Manager non trouvé' });
        res.json(manager);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

// UPDATE
router.put('/:id', validate(managerValidationSchema), async (req, res) => {
    try {
        const manager = await Manager.findByIdAndUpdate(req.params.id, req.body, { new: true });
        if (!manager) return res.status(404).json({ message: 'Manager non trouvé' });
        res.json(manager);
    } catch (err) {
        res.status(400).json({ message: err.message });
    }
});

// DELETE
router.delete('/:id', async (req, res) => {
    try {
        const result = await Manager.findByIdAndDelete(req.params.id);
        if (!result) return res.status(404).json({ message: 'Manager non trouvé' });
        res.json({ message: 'Manager supprimé' });
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});
router.put('/:managerId/assign-team/:teamId', async (req, res) => {
    try {
        const manager = await managerService.assignTeamToManager(
            req.params.managerId,
            req.params.teamId
        );
        res.json(manager); // Retourne le manager mis à jour
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});
router.get('/by-team/:teamId', async (req, res) => {
    try {
        const manager = await managerService.getManagerByTeamId(req.params.teamId);
        if (!manager) {
            return res.status(404).json({ message: 'Manager not found for this team' });
        }
        res.json(manager);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});
router.get('/exists/:managerId', async (req, res) => {
    try {
        const exists = await managerService.managerExists(req.params.managerId);
        res.json({ exists });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

module.exports = router;
