import { shallow, configure } from 'enzyme';
import Home1 from '../components/home1';
import { render, screen } from '@testing-library/react';
import Adapter from 'enzyme-adapter-react-16';

configure({adapter: new Adapter()});

test('home1', () => {
    const component = shallow(<Home1/>);
    const insideComponent = component.find('.home1')
    expect(insideComponent.length).toBe(1);
})

test('renders', () => {
    render(<Home1/>);
    const linkElement = screen.getByText(/Welcome To BlueStone Library/i);
    expect(linkElement).toBeInTheDocument();
  }); 

  test('renders', () => {
    render(<Home1/>);
    const linkElement = screen.getByText(/Your Own Library/i);
    expect(linkElement).toBeInTheDocument();
  });


  /*describe('Paragraph', () => {
    it('should render children inside a p tag', () => {
      const wrapper = shallow(<Home>Your Own Library</Home>)
      const paragraph = wrapper.find('p')
      expect(paragraph).toHaveLength(3)
      expect(paragraph.text()).toEqual('Your Own Library')
    })
  })*/