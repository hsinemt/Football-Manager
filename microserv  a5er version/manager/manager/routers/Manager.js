const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');

// Define the Manager schema
const managerSchema = new mongoose.Schema({
  nom: { type: String, required: true },
  prenom: { type: String, required: true },
  equipe: { type: String, default: null },
  nb_match_carriere: { type: Number, default: 0 },
  nb_victoire: { type: Number, default: 0 },
  nb_titre: { type: Number, default: 0 },
  historique: { type: [String], default: [] }
});

const Manager = mongoose.model('Manager', managerSchema);

// GET /managers - Fetch all managers
router.get('/', async (req, res) => {
  try {
    const managers = await Manager.find();
    // Map _id to id in the response
    const managersWithId = managers.map(manager => ({
      id: manager._id.toString(),
      nom: manager.nom,
      prenom: manager.prenom,
      equipe: manager.equipe,
      nb_match_carriere: manager.nb_match_carriere,
      nb_victoire: manager.nb_victoire,
      nb_titre: manager.nb_titre,
      historique: manager.historique
    }));
    console.log('GET /managers called, returning:', managersWithId);
    res.json(managersWithId);
  } catch (err) {
    console.error('Error fetching managers:', err);
    res.status(500).json({ message: 'Server error' });
  }
});

// POST /managers - Create a new manager
router.post('/', async (req, res) => {
  try {
    const managerData = {
      nom: req.body.nom,
      prenom: req.body.prenom,
      equipe: req.body.equipe || null,
      nb_match_carriere: req.body.nb_match_carriere || 0,
      nb_victoire: req.body.nb_victoire || 0,
      nb_titre: req.body.nb_titre || 0,
      historique: req.body.historique || []
    };
    const manager = new Manager(managerData);
    await manager.save();
    const managerWithId = {
      id: manager._id.toString(),
      ...managerData
    };
    console.log('POST /managers created:', managerWithId);
    res.status(201).json(managerWithId);
  } catch (err) {
    console.error('Error creating manager:', err);
    res.status(500).json({ message: 'Server error' });
  }
});

// PUT /managers/:id - Update a manager
router.put('/:id', async (req, res) => {
  try {
    const id = req.params.id;
    const manager = await Manager.findById(id);
    if (!manager) {
      console.log('PUT /managers/:id failed, manager not found:', id);
      return res.status(404).json({ message: 'Manager not found' });
    }
    manager.nom = req.body.nom;
    manager.prenom = req.body.prenom;
    manager.equipe = req.body.equipe || null;
    manager.nb_match_carriere = req.body.nb_match_carriere || 0;
    manager.nb_victoire = req.body.nb_victoire || 0;
    manager.nb_titre = req.body.nb_titre || 0;
    manager.historique = req.body.historique || [];
    await manager.save();
    const managerWithId = {
      id: manager._id.toString(),
      nom: manager.nom,
      prenom: manager.prenom,
      equipe: manager.equipe,
      nb_match_carriere: manager.nb_match_carriere,
      nb_victoire: manager.nb_victoire,
      nb_titre: manager.nb_titre,
      historique: manager.historique
    };
    console.log('PUT /managers/:id updated:', managerWithId);
    res.json(managerWithId);
  } catch (err) {
    console.error('Error updating manager:', err);
    res.status(500).json({ message: 'Server error' });
  }
});

// DELETE /managers/:id - Delete a manager
router.delete('/:id', async (req, res) => {
  try {
    const id = req.params.id;
    const manager = await Manager.findByIdAndDelete(id);
    if (!manager) {
      console.log('DELETE /managers/:id failed, manager not found:', id);
      return res.status(404).json({ message: 'Manager not found' });
    }
    console.log('DELETE /managers/:id deleted, ID:', id);
    res.status(204).send();
  } catch (err) {
    console.error('Error deleting manager:', err);
    res.status(500).json({ message: 'Server error' });
  }
});

// PATCH /managers/:id/assign-team - Assign a team to a manager
router.patch('/:id/assign-team', async (req, res) => {
  try {
    const id = req.params.id;
    const manager = await Manager.findById(id);
    if (!manager) {
      console.log('PATCH /managers/:id/assign-team failed, manager not found:', id);
      return res.status(404).json({ message: 'Manager not found' });
    }
    manager.equipe = req.body.equipe;
    await manager.save();
    const managerWithId = {
      id: manager._id.toString(),
      nom: manager.nom,
      prenom: manager.prenom,
      equipe: manager.equipe,
      nb_match_carriere: manager.nb_match_carriere,
      nb_victoire: manager.nb_victoire,
      nb_titre: manager.nb_titre,
      historique: manager.historique
    };
    console.log('PATCH /managers/:id/assign-team updated:', managerWithId);
    res.json(managerWithId);
  } catch (err) {
    console.error('Error assigning team to manager:', err);
    res.status(500).json({ message: 'Server error' });
  }
});

module.exports = router;