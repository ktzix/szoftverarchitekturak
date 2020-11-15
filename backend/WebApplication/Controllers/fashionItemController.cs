using System;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Enums;
using WebApplication.Interfaces;
using WebApplication.Models;

namespace WebApplication.Controllers
{ 
    [Route("api/[controller]")]
    public class fashionItemController : Controller
    {
        private readonly IFashionRepository _fashionRepository;

        public fashionItemController(IFashionRepository fashionRepository)
        {
            _fashionRepository = fashionRepository;
        }
        
        [HttpGet]
        public IActionResult List()
        {
            return Ok(_fashionRepository.All);
        }
        
        [HttpPost]
        public IActionResult Create([FromBody] FashionItem item)
        {
            try
            {
                if (item == null || !ModelState.IsValid)
                {
                    return BadRequest(ErrorCode.FashionItemNameAndNotesRequired.ToString());
                }
                bool itemExists = _fashionRepository.DoesItemExist(item.Id);
                if (itemExists)
                {
                    return StatusCode(StatusCodes.Status409Conflict, ErrorCode.FashionItemIdInUse.ToString());
                }
                _fashionRepository.Insert(item);
            }
            catch (Exception)
            {
                return BadRequest(ErrorCode.CouldNotCreateItem.ToString());
            }
            return Ok(item);
        }
        
        
        [HttpPut]
        public IActionResult Edit([FromBody] FashionItem item)
        {
            try
            {
                if (item == null || !ModelState.IsValid)
                {
                    return BadRequest(ErrorCode.FashionItemNameAndNotesRequired.ToString());
                }
                var existingItem = _fashionRepository.Find(item.Id);
                if (existingItem == null)
                {
                    return NotFound(ErrorCode.RecordNotFound.ToString());
                }
                _fashionRepository.Update(item);
            }
            catch (Exception)
            {
                return BadRequest(ErrorCode.CouldNotUpdateItem.ToString());
            }
            return NoContent();
        }
        
        [HttpDelete("{id}")]
        public IActionResult Delete(string id)
        {
            try
            {
                var item = _fashionRepository.Find(id);
                if (item == null)
                {
                    return NotFound(ErrorCode.RecordNotFound.ToString());
                }
                _fashionRepository.Delete(id);
            }
            catch (Exception)
            {
                return BadRequest(ErrorCode.CouldNotDeleteItem.ToString());
            }
            return NoContent();
        }
        
        
        
    }
}